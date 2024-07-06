public enum Messages {
    nodata("Поки тут немає ніяких тваринок"),
    fileErr("Не вдалося считати файл"),
    greeting("Привіт! Це прихисток для тварин"),
    name("Введіть ім'я тварини"),
    age("Введіть вік"),
    type("Введіть вид"),
    show("Показати усіх тваринок"),
    commands("1. Додати тваринку (add)\n2. Забрати тваринку (remove)\n3. Показати усіх (show)\n4. Вийти (exit)"),
    success("Операція успішна"),
    unknown("Невідома команда"),
    notfound("Тваринки з таким ім'ям не знайдено"),
    tryAgain("Спробуй ще раз"),
    bye("До зустрічі!");


    private final String msg;

    Messages(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }

    public void print() {
        System.out.println(msg);
    }


}
