package demo;

import lombok.*;

@Getter  @Setter   @ToString   @NoArgsConstructor   @AllArgsConstructor
@Data // Getter , Setter , ToString
public class Person {

    private String name;
    private Integer age;

    public static void main(String[] args) {
        Person person = new Person("홍" , 20);
        System.out.println(person.getAge());
        person.setName("이순신");
        System.out.println(person.toString());

    }
}
