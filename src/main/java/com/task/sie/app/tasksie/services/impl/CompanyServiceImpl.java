package com.task.sie.app.tasksie.services.impl;

import com.task.sie.app.tasksie.dto.BaseEmail;
import com.task.sie.app.tasksie.dto.ResponseError;
import com.task.sie.app.tasksie.dto.company.CompanyConverter;
import com.task.sie.app.tasksie.dto.company.CompanyDto;
import com.task.sie.app.tasksie.dto.company.quote.CompanyQuoteRequest;
import com.task.sie.app.tasksie.dto.company.LegalRepresentativeDto;
import com.task.sie.app.tasksie.enums.EnumRol;
import com.task.sie.app.tasksie.enums.EnumStatus;
import com.task.sie.app.tasksie.model.company.Company;
import com.task.sie.app.tasksie.model.company.CompanyQuote;
import com.task.sie.app.tasksie.repository.CompanyQuoteRepository;
import com.task.sie.app.tasksie.model.user.Person;
import com.task.sie.app.tasksie.model.user.Rol;
import com.task.sie.app.tasksie.model.user.User;
import com.task.sie.app.tasksie.repository.CompanyRepository;
import com.task.sie.app.tasksie.repository.PersonRepository;
import com.task.sie.app.tasksie.repository.RolRepository;
import com.task.sie.app.tasksie.repository.UserRepository;
import com.task.sie.app.tasksie.services.CompanyService;
import com.task.sie.app.tasksie.services.SendEmailService;
import com.task.sie.app.tasksie.services.UserService;
import com.task.sie.app.tasksie.utils.GeneratePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SendEmailService sendEmailService;

    private static final String COMPANY_NOT_EXISTS = "La empresa no existe";

    @Override
    public List<CompanyDto> index() {
        return companyRepository.findAll().stream().map(CompanyConverter::toDto).collect(Collectors.toList());
    }

    private Random random = new Random();

    @Override
    @Transactional
    public CompanyDto create(CompanyDto dto) {
        Company company = CompanyConverter.toModel(dto);
        company.setStatus(EnumStatus.REG);
        return CompanyConverter.toDto(companyRepository.save(company));
    }

    @Override
    public CompanyDto update(Long companyId, CompanyDto dto) throws ResponseError {
        Optional<Company> company = companyRepository.findById(companyId);
        if(company.isEmpty())
            throw new ResponseError(COMPANY_NOT_EXISTS);

        company.get().setName(dto.getName());
        company.get().setCommercialName(dto.getCommercialName());
        company.get().setDocument(dto.getDocument());
        company.get().setPhone(dto.getPhone());
        company.get().setMobile(dto.getMobile());
        company.get().getLegalRepresentative().getPerson().setName(dto.getLegalRepresentative().getPerson().getName());
        company.get().getLegalRepresentative().getPerson().setLastName(dto.getLegalRepresentative().getPerson().getLastName());
        company.get().getLegalRepresentative().getPerson().setDocument(dto.getLegalRepresentative().getPerson().getDocument());
        return CompanyConverter.toDto(companyRepository.save(company.get()));
    }
    @Override
    public CompanyDto delete(Long companyId) throws ResponseError {
        Optional<Company> company = companyRepository.findById(companyId);
        if(company.isEmpty())
            throw new ResponseError(COMPANY_NOT_EXISTS);
        company.get().setStatus(EnumStatus.DEL);
        return CompanyConverter.toDto(companyRepository.save(company.get()));
    }

    @Override
    @Transactional()
    public Boolean activateCompany(Long companyId, LegalRepresentativeDto legalRepresentativeDto) throws ResponseError {
        Optional<Company> company = companyRepository.findById(companyId);
        if(company.isEmpty())
            throw new ResponseError(COMPANY_NOT_EXISTS);

        if(company.get().getStatus() == EnumStatus.ACT)
            throw new ResponseError("La empresa ya se encuentra activa");

        if(company.get().getStatus() == EnumStatus.DEL)
            throw new ResponseError("La empresa fue eliminada");

        try {
            String password = GeneratePassword.generateRandomSpecialCharacters(10);
            User user = userRepository.save(generateUserFromLegalRepresentativeDto(legalRepresentativeDto, password));
            personRepository.save(Person.builder()
                    .document(legalRepresentativeDto.getPerson().getDocument())
                    .lastName(legalRepresentativeDto.getPerson().getLastName())
                    .name(legalRepresentativeDto.getPerson().getName())
                    .user(user)
                    .build());
            company.get().setStatus(EnumStatus.ACT);
            company.get().getUsers().add(user);
            companyRepository.save(company.get());
            sendEmailActivateUserBusinessManager(user.getUsername(), password, user.getEmail());
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean existCompanyByName(String name) {
        return companyRepository.existsByName(name);
    }

    @Override
    public boolean existsByCommercialName(String commercialName) {
        return companyRepository.existsByCommercialName(commercialName);
    }

    @Override
    public boolean existCompanyByDocument(String document) {
        return companyRepository.existsByDocument(document);
    }

    private User generateUserFromLegalRepresentativeDto(LegalRepresentativeDto legalRepresentativeDto, String password){

        int codeRandom = random.nextInt(900) + 100;

        String username = legalRepresentativeDto.getPerson().getName().concat(legalRepresentativeDto.getPerson().getLastName().substring(0,2)).concat(String.valueOf(codeRandom));

        Rol rolBusinessManager = rolRepository.findByName(EnumRol.ROLE_BUSINESS_MANAGER);

        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .changePassword(true)
                .email(legalRepresentativeDto.getEmail())
                .roles(Set.of(rolBusinessManager))
                .status(EnumStatus.ACT)
                .build();
    }

    private void sendEmailActivateUserBusinessManager(String username, String password, String email){
        BaseEmail baseEmail = BaseEmail.builder()
                .to(email)
                .template("new_user_app_bussines_mananger.html")
                .subject("Activacion cuenta")
                .params(new HashMap<>())
                .build();
        baseEmail.getParams().put("nameUser", username);
        baseEmail.getParams().put("password", password);

        sendEmailService.sentEmailActivateUser(baseEmail);

    }

}
