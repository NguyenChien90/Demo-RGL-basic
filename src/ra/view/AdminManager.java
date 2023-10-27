package ra.view;

import ra.config.Validate;

public class AdminManager {
    public void menuAmin() {
        do {
            System.out.println("\nXin chao: "+ Home.userLogin.getName());
            System.out.println("------ MENU ADMIN ------");
            System.out.println("1. QUAN LY SAN PHAM");
            System.out.println("2. QUAN LY DANH MUC");
            System.out.println("3. QUAN LY NGUOI DUNG");
            System.out.println("0. Log out");
            System.out.print("Lựa chọn (0/1/2) ");
            switch (Validate.validateInt()) {

                case 0:
                    Home.userLogin = null;
                    new Home().menuHome();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (true);
    }
}
