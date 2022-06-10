package l2s.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@ToString //(callSuper = true)
@EqualsAndHashCode //(callSuper = true)
@SuperBuilder
public class LandingEntity extends OrgEntity {
     String landingId;
     String sourceSystemId;
     String sourceEntityId;
}