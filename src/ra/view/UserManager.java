package ra.view;

import ra.config.Validate;

public class UserManager {
    public void menuUser() {
                do {
                    System.out.println("\nXin chao: "+ Home.userLogin.getName());
                    System.out.println("------ MENU USER ------");
                    System.out.println("1. Hien thi danh sach san pham");
                    System.out.println("2. Tim kiem san pham theo ten");
                    System.out.println("3. Thay doi mat khau");
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
