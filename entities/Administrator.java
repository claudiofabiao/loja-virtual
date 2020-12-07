package shop.entities;

import shop.App;

public class Administrator extends User {
    public Administrator(String name, String surname, String email, String password) {
        super(name, surname, email, password);
    }

    public static Administrator findByCredentials(String email, String password) {
        Administrator foundAdministrator = null;

        for (Administrator administrator : App.getInstance().getAdministrators()) {
            if (administrator.getEmail().equals(email) && administrator.getPassword().equals(password)) {
                foundAdministrator = administrator;
            }
        }

        return foundAdministrator;
    }
}