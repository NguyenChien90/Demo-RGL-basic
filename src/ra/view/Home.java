package ra.view;
import ra.config.Validate;
import ra.model.RoleName;
import ra.model.Users;
import ra.sevice.user.IUserService;
import ra.sevice.user.UserServiceIMPL;
public class Home {
    IUserService userService = new UserServiceIMPL();
    public static Users userLogin;
    public void menuHome() {
                do {
                    System.out.println("DANH SACH NGUOI DUNG");
                    System.out.println();
                    for (Users users : userService.findAll()) {
                        System.out.println(users);
                    }
                    System.out.println("------ MENU ------");
                    System.out.println("1. Login");
                    System.out.println("2. Register");
                    System.out.println("0. Thoat");
                    System.out.print("Lựa chọn (0/1/2) ");
                    switch (Validate.validateInt()) {
                        case 1:
                            login();
                            break;
                        case 2:
                            register();
                            break;
                        case 0:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                            break;
                    }
                } while (true);
            }

    private void login() {
        System.out.println("*** FORM LOGIN ***");
        System.out.println("Nhap ten tai khoan: ");
        String username = Validate.validateString();
        System.out.println("Nhap mat khau: ");
        String pass = Validate.validateString();
        Users users = userService.checkLogin(username,pass);
        //kiem tra
        if (users == null){
            System.out.println("Sai ten tai khoan hoac mat khau");
        }else {
            //dung ten tk voi mk
            if (users.getRole().equals(RoleName.AMIN)){
                userLogin = users;
                // chuyen den trang quanr ly ADMIN
                System.out.println("Dang nhap thanh cong");
                new AdminManager().menuAmin();
            }else {
                if (users.isStatus()){
                    userLogin = users;
                    // chuyen den trang user
                    System.out.println("Dang nhap thanh cong");
                    new UserManager().menuUser();
                }else {
                    System.out.println("Tai khoan cua ban dang bi khoa");
                }
            }
        }
    }

    private void register() {
        System.out.println("*** FORM REGISTER ***");
        Users users = new Users();
        users.setId(userService.getNewId());
        System.out.println("Id: "+users.getId());
        System.out.println("Nhap ho ten: ");
        users.setName(Validate.validateString());
        System.out.println("Nhap ten tai khoan");
        while (true){
            String username = Validate.validateString();
            if (userService.existUsername(username)){
                System.out.println("Ten dang nhap da ton tai moi nhap lai");
            }else {
                users.setUsername(username);
                break;
            }
        }
        System.out.println("Nhap mat khau");
        users.setPassword(Validate.validateString());
        System.out.println("Nhap lai mat khau");
        while (true){
            String repeatPass = Validate.validateString();
            if (users.getPassword().equals(repeatPass)){
                break;
            }else {
                System.out.println("Mat khau khong khop moi nhai lai");
            }
        }
        System.out.println("Nhap email");
        while (true){
            String email = Validate.validateEmail();
            if (userService.existEmail(email)){
                System.out.println("Email da ton tai moi nhap lai");
            }else {
                users.setEmail(email);
                break;
            }
        }
        userService.save(users);
        System.out.println("Tao tai khoan thanh cong");

    }

}
