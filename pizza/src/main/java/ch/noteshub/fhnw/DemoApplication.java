package ch.noteshub.fhnw;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.noteshub.fhnw.data.domain.Module;
import ch.noteshub.fhnw.business.service.ModuleService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;

import ch.noteshub.fhnw.data.domain.Role;
import ch.noteshub.fhnw.business.service.DegreeService;
import ch.noteshub.fhnw.business.service.TeacherService;
import ch.noteshub.fhnw.business.service.LocationService;
import ch.noteshub.fhnw.business.service.UserService;
import ch.noteshub.fhnw.business.service.NotesService;
import ch.noteshub.fhnw.data.domain.Degree;
import ch.noteshub.fhnw.data.domain.Teacher;
import ch.noteshub.fhnw.data.domain.Location;
import ch.noteshub.fhnw.data.domain.User;
import ch.noteshub.fhnw.data.repository.RoleRepository;
import ch.noteshub.fhnw.data.repository.UserRepository;
import ch.noteshub.fhnw.data.domain.Notes;

@SpringBootApplication
@RestController
@Hidden // Hide this controller from the Swagger UI
public class DemoApplication {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private DegreeService degreeService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotesService notesService;

        @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
    // Use this method to initialize placeholder data without using Postman
    @PostConstruct
    private void initPlaceholderData() throws Exception {


                // Initialize Locations
        Location basel = new Location();
        basel.setLocationName("Basel");
        locationService.addLocation(basel);
        
        Location muttenz = new Location();
        muttenz.setLocationName("Muttenz");
        locationService.addLocation(muttenz);

        Location bruug = new Location();
        bruug.setLocationName("Brugg");
        locationService.addLocation(bruug);

        Location olten = new Location();
        olten.setLocationName("Olten");
        locationService.addLocation(olten);
        


       // Degree 1: Bachelor of Science in Computer Science
Degree computerScience = new Degree();
computerScience.setDegreeName("Bachelor of Science in Computer Science");
computerScience.setLocation(basel);
degreeService.addDegree(computerScience);

Module compSci1 = new Module();
compSci1.setModuleName("Introduction to Programming");
compSci1.setModuleType("Bachelor");
compSci1.setModulePeriod("2023");
compSci1.setModuleTeachingLanguage("English");
compSci1.setDegree(computerScience);
moduleService.addModule(compSci1);

Module compSci2 = new Module();
compSci2.setModuleName("Data Structures and Algorithms");
compSci2.setModuleType("Bachelor");
compSci2.setModulePeriod("2023");
compSci2.setModuleTeachingLanguage("English");
compSci2.setDegree(computerScience);
moduleService.addModule(compSci2);

Module compSci3 = new Module();
compSci3.setModuleName("Database Systems");
compSci3.setModuleType("Bachelor");
compSci3.setModulePeriod("2023");
compSci3.setModuleTeachingLanguage("English");
compSci3.setDegree(computerScience);
moduleService.addModule(compSci3);

Module compSci4 = new Module();
compSci4.setModuleName("Operating Systems");
compSci4.setModuleType("Bachelor");
compSci4.setModulePeriod("2023");
compSci4.setModuleTeachingLanguage("English");
compSci4.setDegree(computerScience);
moduleService.addModule(compSci4);

// Degree 2: Master of Business Administration
Degree businessAdministration = new Degree();
businessAdministration.setDegreeName("Master of Business Administration");
businessAdministration.setLocation(muttenz);
degreeService.addDegree(businessAdministration);

Module BA1 = new Module();
BA1.setModuleName("Strategic Management");
BA1.setModuleType("Master");
BA1.setModulePeriod("2024");
BA1.setModuleTeachingLanguage("German");
BA1.setDegree(businessAdministration);
moduleService.addModule(BA1);

Module BA2 = new Module();
BA2.setModuleName("Financial Accounting");
BA2.setModuleType("Master");
BA2.setModulePeriod("2024");
BA2.setModuleTeachingLanguage("German");
BA2.setDegree(businessAdministration);
moduleService.addModule(BA2);

Module BA3 = new Module();
BA3.setModuleName("Marketing Management");
BA3.setModuleType("Master");
BA3.setModulePeriod("2024");
BA3.setModuleTeachingLanguage("German");
BA3.setDegree(businessAdministration);
moduleService.addModule(BA3);

Module BA4 = new Module();
BA4.setModuleName("Human Resource Management");
BA4.setModuleType("Master");
BA4.setModulePeriod("2024");
BA4.setModuleTeachingLanguage("German");
BA4.setDegree(businessAdministration);
moduleService.addModule(BA4);

// Degree 3: Bachelor of Business Information Technology
Degree businessInfoTech = new Degree();
businessInfoTech.setDegreeName("Bachelor of Business Information Technology");
businessInfoTech.setLocation(basel);
degreeService.addDegree(businessInfoTech);

Module BIT1 = new Module();
BIT1.setModuleName("Information Systems");
BIT1.setModuleType("Bachelor");
BIT1.setModulePeriod("2023");
BIT1.setModuleTeachingLanguage("English");
BIT1.setDegree(businessInfoTech);
moduleService.addModule(BIT1);

Module BIT2 = new Module();
BIT2.setModuleName("Enterprise Architecture");
BIT2.setModuleType("Bachelor");
BIT2.setModulePeriod("2023");
BIT2.setModuleTeachingLanguage("English");
BIT2.setDegree(businessInfoTech);
moduleService.addModule(BIT2);

Module BIT3 = new Module();
BIT3.setModuleName("IT Project Management");
BIT3.setModuleType("Bachelor");
BIT3.setModulePeriod("2023");
BIT3.setModuleTeachingLanguage("English");
BIT3.setDegree(businessInfoTech);
moduleService.addModule(BIT3);

Module BIT4 = new Module();
BIT4.setModuleName("Business Process Modeling");
BIT4.setModuleType("Bachelor");
BIT4.setModulePeriod("2023");
BIT4.setModuleTeachingLanguage("English");
BIT4.setDegree(businessInfoTech);
moduleService.addModule(BIT4);

// Degree 4: Master of Data Science
Degree dataScience = new Degree();
dataScience.setDegreeName("Master of Data Science");
dataScience.setLocation(basel);
degreeService.addDegree(dataScience);

Module DS1 = new Module();
DS1.setModuleName("Machine Learning");
DS1.setModuleType("Master");
DS1.setModulePeriod("2024");
DS1.setModuleTeachingLanguage("English");
DS1.setDegree(dataScience);
moduleService.addModule(DS1);

Module DS2 = new Module();
DS2.setModuleName("Big Data Analytics");
DS2.setModuleType("Master");
DS2.setModulePeriod("2024");
DS2.setModuleTeachingLanguage("English");
DS2.setDegree(dataScience);
moduleService.addModule(DS2);

Module DS3 = new Module();
DS3.setModuleName("Data Visualization");
DS3.setModuleType("Master");
DS3.setModulePeriod("2024");
DS3.setModuleTeachingLanguage("English");
DS3.setDegree(dataScience);
moduleService.addModule(DS3);

Module DS4 = new Module();
DS4.setModuleName("Data Mining");
DS4.setModuleType("Master");
DS4.setModulePeriod("2024");
DS4.setModuleTeachingLanguage("English");
DS4.setDegree(dataScience);
moduleService.addModule(DS4);

Degree mechanicalEngineering = new Degree();
mechanicalEngineering.setDegreeName("Bachelor of Mechanical Engineering");
mechanicalEngineering.setLocation(bruug);
degreeService.addDegree(mechanicalEngineering);

Module mechEng1 = new Module();
mechEng1.setModuleName("Thermodynamics");
mechEng1.setModuleType("Bachelor");
mechEng1.setModulePeriod("2023");
mechEng1.setModuleTeachingLanguage("German");
mechEng1.setDegree(mechanicalEngineering);
moduleService.addModule(mechEng1);

Module mechEng2 = new Module();
mechEng2.setModuleName("Fluid Mechanics");
mechEng2.setModuleType("Bachelor");
mechEng2.setModulePeriod("2023");
mechEng2.setModuleTeachingLanguage("German");
mechEng2.setDegree(mechanicalEngineering);
moduleService.addModule(mechEng2);

Module mechEng3 = new Module();
mechEng3.setModuleName("Material Science");
mechEng3.setModuleType("Bachelor");
mechEng3.setModulePeriod("2023");
mechEng3.setModuleTeachingLanguage("German");
mechEng3.setDegree(mechanicalEngineering);
moduleService.addModule(mechEng3);

Module mechEng4 = new Module();
mechEng4.setModuleName("Mechanical Design");
mechEng4.setModuleType("Bachelor");
mechEng4.setModulePeriod("2023");
mechEng4.setModuleTeachingLanguage("German");
mechEng4.setDegree(mechanicalEngineering);
moduleService.addModule(mechEng4);

Degree environmentalEngineering = new Degree();
environmentalEngineering.setDegreeName("Master of Environmental Engineering");
environmentalEngineering.setLocation(olten);
degreeService.addDegree(environmentalEngineering);

Module envEng1 = new Module();
envEng1.setModuleName("Environmental Impact Assessment");
envEng1.setModuleType("Master");
envEng1.setModulePeriod("2024");
envEng1.setModuleTeachingLanguage("English");
envEng1.setDegree(environmentalEngineering);
moduleService.addModule(envEng1);

Module envEng2 = new Module();
envEng2.setModuleName("Sustainable Design");
envEng2.setModuleType("Master");
envEng2.setModulePeriod("2024");
envEng2.setModuleTeachingLanguage("English");
envEng2.setDegree(environmentalEngineering);
moduleService.addModule(envEng2);

Module envEng3 = new Module();
envEng3.setModuleName("Water Resources Management");
envEng3.setModuleType("Master");
envEng3.setModulePeriod("2024");
envEng3.setModuleTeachingLanguage("English");
envEng3.setDegree(environmentalEngineering);
moduleService.addModule(envEng3);

Module envEng4 = new Module();
envEng4.setModuleName("Waste Management");
envEng4.setModuleType("Master");
envEng4.setModulePeriod("2024");
envEng4.setModuleTeachingLanguage("English");
envEng4.setDegree(environmentalEngineering);
moduleService.addModule(envEng4);

      

        // Initialize Teachers
        Teacher teacher = new Teacher();
        teacher.setTeacherFirstname("John");
        teacher.setTeacherLastname("Doe");
        teacherService.addTeacher(teacher);

        teacher = new Teacher();
        teacher.setTeacherFirstname("Jane");
        teacher.setTeacherLastname("Smith");
        teacherService.addTeacher(teacher);


        // Initialize Roles

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        // Initialize Users

        User user = new User();
        user.setUserUsername("myuser");
        user.setUserPassword(passwordEncoder.encode("password"));
        user.setUserFirstname("UserFirstName");
        user.setUserLastname("UserLastName");
        user.setUserEmail("user@example.com");
        user.setEnabled(true);
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);


        User admin = new User();
        admin.setUserUsername("myadmin");
        admin.setUserPassword(passwordEncoder.encode("password"));
        admin.setUserFirstname("AdminFirstName");
        admin.setUserLastname("AdminLastName");
        admin.setUserEmail("admin@example.com");
        admin.setEnabled(true);
        admin.setRoles(new HashSet<>(Arrays.asList(userRole, adminRole)));
        userRepository.save(admin);

        // Initialize Users

User user1 = new User();
user1.setUserUsername("eyeloegrue");
user1.setUserPassword(passwordEncoder.encode("password1"));
user1.setUserFirstname("Emre");
user1.setUserLastname("Yeloegrue");
user1.setUserEmail("emre.yeloegrue@example.com");
user1.setEnabled(true);
user1.setRoles(new HashSet<>(Arrays.asList(userRole)));
userRepository.save(user1);

User user2 = new User();
user2.setUserUsername("djovial");
user2.setUserPassword(passwordEncoder.encode("password2"));
user2.setUserFirstname("Doris");
user2.setUserLastname("Jovial");
user2.setUserEmail("doris.jovial@example.com");
user2.setEnabled(true);
user2.setRoles(new HashSet<>(Arrays.asList(userRole)));
userRepository.save(user2);

User user3 = new User();
user3.setUserUsername("sweber");
user3.setUserPassword(passwordEncoder.encode("password3"));
user3.setUserFirstname("Sarah");
user3.setUserLastname("Weber");
user3.setUserEmail("sarah.weber@example.com");
user3.setEnabled(true);
user3.setRoles(new HashSet<>(Arrays.asList(userRole)));
userRepository.save(user3);

User user4 = new User();
user4.setUserUsername("orahiel");
user4.setUserPassword(passwordEncoder.encode("password4"));
user4.setUserFirstname("Omar");
user4.setUserLastname("Rahiel");
user4.setUserEmail("omar.rahiel@example.com");
user4.setEnabled(true);
user4.setRoles(new HashSet<>(Arrays.asList(userRole)));
userRepository.save(user4);

User user5 = new User();
user5.setUserUsername("jdoe");
user5.setUserPassword(passwordEncoder.encode("password5"));
user5.setUserFirstname("John");
user5.setUserLastname("Doe");
user5.setUserEmail("john.doe@example.com");
user5.setEnabled(true);
user5.setRoles(new HashSet<>(Arrays.asList(userRole)));
userRepository.save(user5);

User user6 = new User();
user6.setUserUsername("jane.smith");
user6.setUserPassword(passwordEncoder.encode("password6"));
user6.setUserFirstname("Jane");
user6.setUserLastname("Smith");
user6.setUserEmail("jane.smith@example.com");
user6.setEnabled(true);
user6.setRoles(new HashSet<>(Arrays.asList(userRole)));
userRepository.save(user6);

User user7 = new User();
user7.setUserUsername("rthompson");
user7.setUserPassword(passwordEncoder.encode("password7"));
user7.setUserFirstname("Robert");
user7.setUserLastname("Thompson");
user7.setUserEmail("robert.thompson@example.com");
user7.setEnabled(true);
user7.setRoles(new HashSet<>(Arrays.asList(userRole)));
userRepository.save(user7);

User user8 = new User();
user8.setUserUsername("emartinez");
user8.setUserPassword(passwordEncoder.encode("password8"));
user8.setUserFirstname("Emily");
user8.setUserLastname("Martinez");
user8.setUserEmail("emily.martinez@example.com");
user8.setEnabled(true);
user8.setRoles(new HashSet<>(Arrays.asList(userRole)));
userRepository.save(user8);

User user9 = new User();
user9.setUserUsername("bbrown");
user9.setUserPassword(passwordEncoder.encode("password9"));
user9.setUserFirstname("Benjamin");
user9.setUserLastname("Brown");
user9.setUserEmail("benjamin.brown@example.com");
user9.setEnabled(true);
user9.setRoles(new HashSet<>(Arrays.asList(userRole)));
userRepository.save(user9);

User user10 = new User();
user10.setUserUsername("mwilson");
user10.setUserPassword(passwordEncoder.encode("password10"));
user10.setUserFirstname("Michael");
user10.setUserLastname("Wilson");
user10.setUserEmail("michael.wilson@example.com");
user10.setEnabled(true);
user10.setRoles(new HashSet<>(Arrays.asList(userRole)));
userRepository.save(user10);
        
        // Initialize Notes
        
        Notes notes = new Notes();
        notes.setNotesTitle("Introduction to Computer Science");
        notes.setUser(user);
        notes.setNotesContent("Computerwissenschaften sind ein faszinierendes Feld, das die theoretischen Grundlagen der Informations- und Computertechnologie erforscht. Es handelt sich um die wissenschaftliche und praktische Herangehensweise an Berechnung und deren Anwendung. In einem Kurs oder einer Einführungsreihe zum Thema “Einführung in die Computerwissenschaften” lernt man die grundlegenden Konzepte des Programmierens, die Funktionsweise von Computersystemen und die theoretischen Modelle, die das Rückgrat der digitalen Technologie bilden.\n" + //
                        "\n" + //
                        "Der Kurs beginnt typischerweise mit den Grundlagen der Programmierung. Hier werden einfache Programmiersprachen wie Python oder Java eingeführt, um die Basis für komplexere Konzepte zu schaffen. Die Studierenden erlernen, wie man einfache Programme schreibt, die Daten verarbeiten, Entscheidungen treffen und die Ergebnisse ausgeben.\n" + //
                        "\n" + //
                        "Neben der Programmierung wird auch die Struktur von Computersystemen behandelt. Dazu gehören Themen wie Betriebssysteme, Netzwerke und die Architektur von Computern. Die Studierenden verstehen, wie Software mit Hardware interagiert und welche Rolle das Betriebssystem bei der Verwaltung der Ressourcen spielt.\n" + //
                        "\n" + //
                        "Ein weiterer wichtiger Bereich ist die Datenstruktur und Algorithmen. Diese Themen sind entscheidend für die Entwicklung effizienter Programme, die große Datenmengen verarbeiten können. Studierende lernen verschiedene Arten von Datenstrukturen kennen, wie Listen, Stapel, Warteschlangen und Bäume. Sie studieren auch Algorithmen zur Suche und Sortierung, um zu verstehen, wie diese auf Datenstrukturen angewendet werden können.\n" + //
                        "\n" + //
                        "Schließlich behandelt ein Einführungskurs auch die Aspekte der Softwareentwicklung und des Software Engineering. Dies umfasst Methoden der Softwareentwicklung, Testverfahren und die Wartung von Softwareprojekten. Ziel ist es, den Studierenden beizubringen, wie man qualitativ hochwertige Software plant, entwickelt und pflegt.\n" + //
                        "\n" + //
                        "Insgesamt bietet eine Einführung in die Computerwissenschaften eine solide Grundlage für diejenigen, die ihre Karriere in verschiedenen Bereichen der Technologie, von der Softwareentwicklung bis hin zur Netzwerkadministration, fortsetzen möchten. Es ist ein erster Schritt auf einem ständig fortschreitenden Weg, der Neugier, logisches Denken und Problemlösungskompetenz fördert.");
        notes.setNotesDate(LocalDate.of(2024, 6, 12));
        notes.setModule(BIT1);
        notesService.addNotes(notes);

      // Initialize Notes for Computer Science Modules

Notes notes1 = new Notes();
notes1.setNotesTitle("Introduction to Programming");
notes1.setUser(user1);
notes1.setNotesContent("Dieser Kurs bietet eine Einführung in die Programmierung und die grundlegenden Konzepte der Informatik. Die Studierenden lernen, wie man einfache Programme in Python schreibt und die Grundstrukturen der Programmiersprache versteht.");
notes1.setNotesDate(LocalDate.of(2024, 1, 15));
notes1.setModule(compSci1);
notesService.addNotes(notes1);

Notes notes2 = new Notes();
notes2.setNotesTitle("Data Structures and Algorithms");
notes2.setUser(user2);
notes2.setNotesContent("In diesem Kurs werden die wichtigsten Datenstrukturen und Algorithmen behandelt. Die Studierenden lernen, wie man effiziente Programme schreibt, die große Datenmengen verarbeiten können.");
notes2.setNotesDate(LocalDate.of(2024, 2, 10));
notes2.setModule(compSci2);
notesService.addNotes(notes2);

Notes notes3 = new Notes();
notes3.setNotesTitle("Database Systems");
notes3.setUser(user3);
notes3.setNotesContent("Dieser Kurs bietet eine umfassende Einführung in Datenbanksysteme. Die Studierenden lernen, wie man relationale Datenbanken entwirft, erstellt und verwaltet.");
notes3.setNotesDate(LocalDate.of(2024, 3, 5));
notes3.setModule(compSci3);
notesService.addNotes(notes3);

Notes notes4 = new Notes();
notes4.setNotesTitle("Operating Systems");
notes4.setUser(user4);
notes4.setNotesContent("In diesem Kurs werden die grundlegenden Konzepte und Komponenten von Betriebssystemen behandelt. Die Studierenden verstehen, wie Betriebssysteme Ressourcen verwalten und Prozesse steuern.");
notes4.setNotesDate(LocalDate.of(2024, 4, 20));
notes4.setModule(compSci4);
notesService.addNotes(notes4);

// Initialize Notes for Business Administration Modules

Notes notes5 = new Notes();
notes5.setNotesTitle("Strategic Management");
notes5.setUser(user5);
notes5.setNotesContent("Dieser Kurs behandelt die grundlegenden Konzepte des strategischen Managements und der Unternehmensführung. Die Studierenden lernen, wie man langfristige Unternehmensstrategien entwickelt und umsetzt.");
notes5.setNotesDate(LocalDate.of(2024, 1, 25));
notes5.setModule(BA1);
notesService.addNotes(notes5);

Notes notes6 = new Notes();
notes6.setNotesTitle("Financial Accounting");
notes6.setUser(user6);
notes6.setNotesContent("In diesem Kurs werden die grundlegenden Prinzipien der Finanzbuchhaltung behandelt. Die Studierenden lernen, wie man Finanzberichte erstellt und interpretiert.");
notes6.setNotesDate(LocalDate.of(2024, 2, 15));
notes6.setModule(BA2);
notesService.addNotes(notes6);

Notes notes7 = new Notes();
notes7.setNotesTitle("Marketing Management");
notes7.setUser(user7);
notes7.setNotesContent("Dieser Kurs bietet eine Einführung in die Prinzipien und Strategien des Marketing-Managements. Die Studierenden lernen, wie man effektive Marketingpläne entwickelt und umsetzt.");
notes7.setNotesDate(LocalDate.of(2024, 3, 10));
notes7.setModule(BA3);
notesService.addNotes(notes7);

Notes notes8 = new Notes();
notes8.setNotesTitle("Human Resource Management");
notes8.setUser(user8);
notes8.setNotesContent("In diesem Kurs werden die wichtigsten Konzepte und Techniken des Personalmanagements behandelt. Die Studierenden lernen, wie man effektive Personalstrategien entwickelt und umsetzt.");
notes8.setNotesDate(LocalDate.of(2024, 4, 5));
notes8.setModule(BA4);
notesService.addNotes(notes8);

// Initialize Notes for Business Information Technology Modules

Notes notes9 = new Notes();
notes9.setNotesTitle("Information Systems");
notes9.setUser(user9);
notes9.setNotesContent("Dieser Kurs bietet eine Einführung in Informationssysteme und deren Anwendung in Unternehmen. Die Studierenden lernen, wie man Informationssysteme entwirft und implementiert.");
notes9.setNotesDate(LocalDate.of(2024, 1, 30));
notes9.setModule(BIT1);
notesService.addNotes(notes9);

Notes notes10 = new Notes();
notes10.setNotesTitle("Enterprise Architecture");
notes10.setUser(user10);
notes10.setNotesContent("In diesem Kurs werden die grundlegenden Konzepte der Unternehmensarchitektur behandelt. Die Studierenden lernen, wie man IT-Systeme in Unternehmen effektiv plant und steuert.");
notes10.setNotesDate(LocalDate.of(2024, 2, 20));
notes10.setModule(BIT2);
notesService.addNotes(notes10);

Notes notes11 = new Notes();
notes11.setNotesTitle("IT Project Management");
notes11.setUser(user1);
notes11.setNotesContent("Dieser Kurs bietet eine Einführung in das Management von IT-Projekten. Die Studierenden lernen, wie man IT-Projekte plant, durchführt und erfolgreich abschließt.");
notes11.setNotesDate(LocalDate.of(2024, 3, 15));
notes11.setModule(BIT3);
notesService.addNotes(notes11);

Notes notes11_2 = new Notes();
notes11_2.setNotesTitle("IT Project Management2");
notes11_2.setUser(user1);
notes11_2.setNotesContent("DDieser Kurs bietet eine Einführung in das Management von IT-Projekten. Die Studierenden lernen, wie man IT-Projekte plant, durchführt und erfolgreich abschließt.");
notes11_2.setNotesDate(LocalDate.of(2024, 3, 14));
notes11_2.setModule(BIT3);
notesService.addNotes(notes11_2); 

Notes notes12 = new Notes();
notes12.setNotesTitle("Business Process Modeling");
notes12.setUser(user2);
notes12.setNotesContent("In diesem Kurs werden die grundlegenden Techniken des Geschäftsprozessmodellierens behandelt. Die Studierenden lernen, wie man Geschäftsprozesse analysiert und optimiert.");
notes12.setNotesDate(LocalDate.of(2024, 4, 10));
notes12.setModule(BIT4);
notesService.addNotes(notes12);

// Initialize Notes for Data Science Modules

Notes notes13 = new Notes();
notes13.setNotesTitle("Machine Learning");
notes13.setUser(user3);
notes13.setNotesContent("Dieser Kurs bietet eine Einführung in das maschinelle Lernen und dessen Anwendung in der Datenanalyse. Die Studierenden lernen, wie man maschinelle Lernmodelle entwickelt und implementiert.");
notes13.setNotesDate(LocalDate.of(2024, 1, 20));
notes13.setModule(DS1);
notesService.addNotes(notes13);

Notes notes14 = new Notes();
notes14.setNotesTitle("Big Data Analytics");
notes14.setUser(user4);
notes14.setNotesContent("In diesem Kurs werden die grundlegenden Konzepte und Techniken der Big Data Analyse behandelt. Die Studierenden lernen, wie man große Datenmengen analysiert und interpretiert.");
notes14.setNotesDate(LocalDate.of(2024, 2, 25));
notes14.setModule(DS2);
notesService.addNotes(notes14);

Notes notes15 = new Notes();
notes15.setNotesTitle("Data Visualization");
notes15.setUser(user5);
notes15.setNotesContent("Dieser Kurs bietet eine Einführung in die Datenvisualisierung und deren Anwendung in der Datenanalyse. Die Studierenden lernen, wie man Daten effektiv visualisiert und präsentiert.");
notes15.setNotesDate(LocalDate.of(2024, 3, 30));
notes15.setModule(DS3);
notesService.addNotes(notes15);

Notes notes16 = new Notes();
notes16.setNotesTitle("Data Mining");
notes16.setUser(user6);
notes16.setNotesContent("In diesem Kurs werden die grundlegenden Techniken des Data Mining behandelt. Die Studierenden lernen, wie man Muster in großen Datenmengen erkennt und analysiert.");
notes16.setNotesDate(LocalDate.of(2024, 4, 25));
notes16.setModule(DS4);
notesService.addNotes(notes16);
}
}