import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }
        System.out.print("Количество несовершеннолетних (т.е. людей младше 18 лет): ");
        long countMinor = persons.stream()
                .filter(value -> value.getAge() < 18)
                .count();
        System.out.println(countMinor);

        System.out.println("\nСписок фамилий призывников (т.е. мужчин от 18 и до 27 лет):");
        persons.stream()
                .filter(value -> value.getSex() == Sex.MAN)
                .filter(value -> value.getAge() >= 18 && value.getAge() <= 27)
                .forEach(System.out::println);

        System.out.println("\nОтсортированный по фамилии список потенциально работоспособных\n" +
                "людей с высшим образованием в выборке (т.е. людей с высшим образованием\n" +
                "от 18 до 60 лет для женщин и до 65 лет для мужчин):");
        persons.stream()
                .filter(value -> value.getAge() > 18)
                .filter(value -> value.getEducation() == Education.HIGHER)
                .filter(value -> value.getSex() == Sex.WOMAN && value.getAge() <= 60 || value.getSex() == Sex.MAN && value.getAge() <= 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .forEach(System.out::println);
    }
}