package l2s.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigInteger;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class LandingCustomer extends LandingEntity{
    String firstName;
    String lastName;
    String phone1;
    String phone2;
    String email1;

}


