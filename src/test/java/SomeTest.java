import com.example.domain.DAO.PersonRepository;
import com.example.domain.entity.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SomeTest {

    @Mock
    private PersonRepository personRepository;
    private static List<Person> personList;

    @Test
    public void getPersons(){
        when(personRepository.findAll()).thenReturn(personList);
        Iterable<Person> list = personRepository.findAll();
        Assert.notNull(list);
    }

    @BeforeAll
    public static void init(){
        personList = new ArrayList<>();
        Person p1 = new Person();
        p1.setFullName("Williams");
        p1.setSex("male");
        p1.setAge(22);
        p1.setAge(19);

        Person p2 = new Person();
        p2.setFullName("Davis");
        p2.setAge(27);
        p2.setSex("male");
        personList.add(p1);
        personList.add(p2);
    }

}
