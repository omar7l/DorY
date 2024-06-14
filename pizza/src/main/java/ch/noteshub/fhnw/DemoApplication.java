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
        


        Degree degree = new Degree();
        degree.setDegreeName("Bachelor of Science in Computer Science");
        degree.setLocation(basel);
        degreeService.addDegree(degree);

        Module module = new Module();
        module.setModuleName("Computer Science");
        module.setModuleType("Bachelor");
        module.setModulePeriod("2023");
        module.setModuleTeachingLanguage("English");
        module.setDegree(degree);
        moduleService.addModule(module);

        degree = new Degree();
        degree.setDegreeName("Master of Business Administration");
        degree.setLocation(muttenz);
        degreeService.addDegree(degree);


        module = new Module();
        module.setModuleName("Business Administration");
        module.setModuleType("Master");
        module.setModulePeriod("2024");
        module.setModuleTeachingLanguage("German");
        module.setDegree(degree);
        moduleService.addModule(module);

      

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
        notesService.addNotes(notes);

        notes = new Notes();
        notes.setNotesTitle("Advanced Business Strategies");
        notes.setUser(admin);
        notes.setNotesContent("Im Rahmen eines Kurses oder einer Weiterbildung zu „Advanced Business Strategies“ werden fortgeschrittene Konzepte und Techniken behandelt, die darauf abzielen, die Wettbewerbsfähigkeit von Unternehmen zu steigern. Dieser Bereich konzentriert sich auf die Analyse, Entwicklung und Umsetzung von Strategien, die Unternehmen dabei helfen, in einem sich schnell verändernden Marktumfeld erfolgreich zu sein.\n" + //
                        "\n" + //
                        "Der Kurs beginnt typischerweise mit einer Vertiefung in die strategische Planung. Die Teilnehmenden lernen, wie man langfristige Ziele setzt, die auf einer gründlichen Analyse der Marktsituation und internen Ressourcen basieren. Es wird besprochen, wie man externe und interne Analysen effektiv durchführt, darunter SWOT-Analysen (Strengths, Weaknesses, Opportunities, Threats) und PESTEL-Analysen (Political, Economic, Social, Technological, Environmental, Legal).\n" + //
                        "\n" + //
                        "Ein weiterer Schwerpunkt liegt auf der Umsetzung von Unternehmensstrategien. Die Studierenden erfahren, wie man strategische Initiativen durch Projektmanagement und Change-Management-Techniken effektiv steuert. Dabei werden auch die Herausforderungen und Widerstände, die bei der Implementierung von neuen Strategien auftreten können, behandelt.\n" + //
                        "\n" + //
                        "Innovationsmanagement ist ein weiterer zentraler Bestandteil des Kurses. Die Teilnehmenden lernen, wie man Innovationsprozesse in einem Unternehmen fördert und steuert. Dies umfasst die Entwicklung von neuen Produkten und Dienstleistungen sowie die Verbesserung bestehender Angebote. Es wird auch die Rolle von Technologie und Datenanalyse in der Unterstützung von Innovationsprozessen hervorgehoben.\n" + //
                        "\n" + //
                        "Ein fortgeschrittener Kurs zu Geschäftsstrategien würde auch Themen wie globale Expansion und internationale Strategien behandeln. Die Teilnehmenden lernen, wie man globale Märkte analysiert und effektive Eintrittsstrategien entwickelt. Zudem wird die Bedeutung von kulturellem Verständnis und lokaler Anpassung in internationalen Märkten diskutiert.\n" + //
                        "\n" + //
                        "Abschließend werden die Teilnehmenden mit der Entwicklung und Präsentation eigener strategischer Pläne beauftragt, welche die im Kurs erlernten Konzepte anwenden. Dieser praxisorientierte Ansatz hilft nicht nur, das Gelernte zu festigen, sondern auch die Fähigkeit zu entwickeln, strategische Entscheidungen zu treffen und diese überzeugend zu kommunizieren.\n" + //
                        "\n" + //
                        "Insgesamt vermittelt ein Kurs zu „Advanced Business Strategies“ die Fähigkeiten und Kenntnisse, die erforderlich sind, um auf höchstem Niveau in der Unternehmensführung erfolgreich zu sein. Er richtet sich an erfahrene Fachkräfte, die ihre strategischen Fähigkeiten erweitern und ihre Unternehmen effektiv durch komplexe und herausfordernde Geschäftsumgebungen führen möchten.");
        notes.setNotesDate(LocalDate.of(2024, 6, 12));
        notesService.addNotes(notes);
    }
}