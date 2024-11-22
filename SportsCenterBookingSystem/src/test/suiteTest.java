package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	AdminTest.class,
	BookingTest.class,
	CmdAddNewRoomTest.class,
	CmdCancelBookingTest.class,
	CmdLoginTest.class,
//	CmdLogoutTest.class,
	CmdMakeBookingTest.class,
	CmdMarkClosingDateTest.class,
	CmdModifyRoomTypePriceTest.class,
	CmdRegisterAccountTest.class,
	CmdViewBookingTest.class,
	CommonTest.class,
	DateAndTimeTest.class,	
//	MainTest.class,
	NormalUserTest.class,
	RoomTest.class,
	RoomTypeTest.class,
	SportsCenterTest.class,
	UserTest.class,
	ViewBookingServiceTest.class
    
})
public class suiteTest {
    // 测试套件类内容
}