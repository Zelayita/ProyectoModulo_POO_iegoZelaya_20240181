package DiegoZelaya_20240181.DiegoZelaya_20240181.Entites;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "TBPROVIDER")
@EqualsAndHashCode
@ToString
public class EntitesProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_provider")
    @SequenceGenerator(name = "seq_provider", sequenceName = "seq_provider", allocationSize = 1)
    @Column(name = "PROVIDERID")
    private Long providerID;

    @Column(name = "PROVIDERNAME")
    private String providerName;

    @Column(name = "PROVIDERPHONE")
    private String providerPhone;

    @Column(name = "PROVIDERADDRESS")
    private String providerAddress;

    @Column(name = "PROVIDEREMAIL")
    private String Email;

    @Column(name = "PROVIDERCODE")
    private String providerCode;

    @Column(name = "PROVIDERSTATUS")
    private Long providerStatus;

    @Column(name = "PROVIDERCOMMENTS")
    private String providerComments;
}
