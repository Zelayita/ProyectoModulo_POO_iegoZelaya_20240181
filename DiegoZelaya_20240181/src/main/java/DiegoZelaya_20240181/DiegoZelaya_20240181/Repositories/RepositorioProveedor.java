package DiegoZelaya_20240181.DiegoZelaya_20240181.Repositories;

import DiegoZelaya_20240181.DiegoZelaya_20240181.Entites.EntitesProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProveedor extends JpaRepository<EntitesProveedor, Long> { }
