package l2s.dao.jdbi;

import l2s.model.LandingCustomer;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RowMapperLandingCustomer implements RowMapper<LandingCustomer> {
    @Override
    public LandingCustomer map(ResultSet rs, StatementContext ctx) throws SQLException {
        var builder = LandingCustomer.builder();
        var landingId = rs.getString("c_landing_id");
        var sourceSystem = rs.getString("c_source_system_id");
        var sourceCustomerId = rs.getString("c_source_customer_id");

        var firstName = rs.getString("c_first_name");
        var lastName = rs.getString("c_last_name");
        var phone1 = rs.getString("c_phone1");
        var phone2 = rs.getString("c_phone2");
        var email1 = rs.getString("c_email1");
        Optional.ofNullable(landingId).ifPresent(builder::landingId); //in super class
        Optional.ofNullable(sourceSystem).ifPresent(builder::sourceSystemId);
        Optional.ofNullable(sourceCustomerId).ifPresent(builder::sourceEntityId);
        //
        Optional.ofNullable(firstName).ifPresent(builder::firstName);
        Optional.ofNullable(lastName).ifPresent(builder::lastName);
        Optional.ofNullable(phone1).ifPresent(builder::phone1);
        Optional.ofNullable(phone2).ifPresent(builder::phone2);
        Optional.ofNullable(email1).ifPresent(builder::email1);
//        Optional.ofNullable(email1).ifPresent(builder::orgEntityId);
        return builder.build();
    }
}
