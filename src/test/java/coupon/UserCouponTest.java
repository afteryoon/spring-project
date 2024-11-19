package coupon;

import com.estsoft.springproject.coupon.DummyCoupon;
import com.estsoft.springproject.coupon.ICoupon;
import com.estsoft.springproject.coupon.User;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class UserCouponTest {

    @Test
    public void 회원_생성() throws Exception{
        //given
        User user = new User("area00");
        assertThat(user.getTotalCouponCount()).isEqualTo(0);

    }

    @Test
    public void 쿠폰이_유요한_경우에만_발급() throws Exception{
        //given
        User user = new User("area00");
        assertThat(user.getTotalCouponCount()).isEqualTo(0);
        //when
        ICoupon coupon = Mockito.mock(ICoupon.class); //mock 객체는 행위 적용 가능
        BDDMockito.given(coupon.isValid())
                .willReturn(true);
        //then
        user.addCoupon(coupon);
        assertThat(user.getTotalCouponCount()).isEqualTo(1);

    }

    @Test
    public void 쿠폰이_유효하지_않을_경우_미발급() throws Exception{
        //given
        User user = new User("area00");
        //when
        ICoupon coupon = Mockito.mock(ICoupon.class);
        BDDMockito.given(coupon.isValid())
                .willReturn(false);
        //then
        user.addCoupon(coupon);
        assertThat(user.getTotalCouponCount()).isEqualTo(0);
    }
}
