import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.*;
import utils.SeleniumTest;

import static org.testng.Assert.fail;

public class JenkinsTest extends SeleniumTest {

    /*
     * 1.	После клика по ссылке «Manage Jenkins» на странице появляется элемент dt с текстом «Manage Users»
     * и элемент dd с текстом «Create/delete/modify users that can log in to this Jenkins».
     */
    @Test
    public void manageUsers(){
        ManageJenkinsPage page = new ManageJenkinsPage(driver);
        page.getManageUsers();
        Assert.assertTrue(page.dtText.isDisplayed());
        Assert.assertTrue(page.ddText.isDisplayed());
    }

    /*
     * 2.	После клика по ссылке, внутри которой содержится элемент dt с текстом «Manage Users»,
     * становится доступна ссылка «Create User».
     */
    @Test
    public void testIsCreateUserAvailable(){
        ManageJenkinsPage page = new ManageJenkinsPage(driver);
        Assert.assertTrue(page.dtManageUsersLink.isDisplayed());
    }


    /*
     * 3.	После клика по ссылке «Create User» появляется форма с тремя полями типа text
     * и двумя полями типа adminPassword, причём все поля должны быть пустыми.
     */
    @Test
    public void testIsFormDisplayed(){
        ManageJenkinsPage managePage = new ManageJenkinsPage(driver);
        managePage.clickManageUser();

        CreateUserPage page = new CreateUserPage(driver);
        Assert.assertTrue(page.testIsFormDisplayed());
        Assert.assertTrue(page.areFieldsEmpty());
    }


    /*
     * 4.	После заполнения полей формы
     * («Username» = «someuser», «Password» = «somepassword», «Confirm adminPassword» = «somepassword»,
     * «Full name» = «Some Full Name», «E-mail address» = «some@addr.dom»)
     * и клика по кнопке с надписью «Create User»
     * на странице появляется строка таблицы (элемент tr),
     * в которой есть ячейка (элемент td) с текстом «someuser».
     */
    @Test
    @Parameters({"username", "password", "fullname", "email"})
    public void testIsUserDisplayed(String username, String password, String fullname, String email){
        AddUserPage page = new AddUserPage(driver);
        page.submitFilledForm(username, password,fullname, email);
        Assert.assertTrue(page.isUserPresentInList(username));
    }


    /*
     * 5.	После клика по ссылке с атрибутом href равным «user/someuser/delete»
     * появляется текст «Are you sure about deleting the user from Jenkins?».
     */
    @Test
    @Parameters("username")
    public void testIsDeleteMessageDisplayed(String username){
        UserListPage listPage = new UserListPage(driver);
        if (listPage.testDeleteUserLink(username)){
            ConfirmDeleteUserPage confirmPage = new ConfirmDeleteUserPage(driver);
            Assert.assertTrue(confirmPage.pageTextContents("Are you sure about deleting the user from Jenkins?"));
        } else fail();
    }


    /*
     * 6.	После клика по кнопке с надписью «Yes»
     * на странице отсутствует строка таблицы (элемент tr),
     * с ячейкой (элемент td) с текстом «someuser».
     * На странице отсутствует ссылка с атрибутом href равным «user/someuser/delete».
     */
    @Test
    @Parameters("username")
    public void testYesAction(String username){
        ConfirmDeleteUserPage confirmPage = new ConfirmDeleteUserPage(driver);
        confirmPage.confirmDeleteUser();

        UserListPage listPage  = new UserListPage(driver);
        Assert.assertFalse(listPage.isDeleteLinkPresent(username));
    }


    /*
     * 7.	{На той же странице, без выполнения каких бы то ни было действий}.
     * На странице отсутствует ссылка с атрибутом href равным «user/admin/delete».
     */
    @Test
    @Parameters("adminUsername")
    public void testIsDeleteLinkAbsent(String adminUsername){
        UserListPage listPage  = new UserListPage(driver);
        Assert.assertFalse(listPage.isDeleteLinkPresent(adminUsername));
    }


    //======================Дополнительные задания (выполняются по желанию, если осталось время):==============================================================================

//    /*
//     * 1.	У всех кнопок (элемент типа button), которые нужно кликать в основной части задания,
//     * цвет фона = #4b758b.
//     */
//    @Test
//    public void testButtonsColor(){
//        List<WebElement> buttons = driver.findElements(By.tagName("button"));
//        fail();
//    }
//
//    /*
//     * 2.	При попытке создать пользователя с пустым (незаполненным) именем
//     * на странице появляется текст «"" is prohibited as a full name for security reasons.»
//     */
//    @Test
//    public void testEmptyUser(){
//        fail();
//    }
//
//    /*
//     * 3.	При клике по ссылке с текстом «ENABLE AUTO REFRESH»
//     * эта ссылка пропадает,
//     * а вместо неё появляется ссылка с текстом «DISABLE AUTO REFRESH».
//     *
//     * При клике по ссылке с текстом «DISABLE AUTO REFRESH»
//     * эта ссылка пропадает,
//     * а вместо неё появляется ссылка с текстом «ENABLE AUTO REFRESH».
//     *
//     * Т.е. эти две ссылки циклически сменяют друг друга.
//     */
//    @Test
//    public void testCyclicLinks(){
//        fail();
//    }
}
