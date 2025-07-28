package DiegoZelaya_20240181.DiegoZelaya_20240181.Service;

import DiegoZelaya_20240181.DiegoZelaya_20240181.Entites.EntitesProveedor;
import DiegoZelaya_20240181.DiegoZelaya_20240181.Exceptions.ExceptionUsuarioNoEncontrado;
import DiegoZelaya_20240181.DiegoZelaya_20240181.Models.DTO.DTOProvedor;
import DiegoZelaya_20240181.DiegoZelaya_20240181.Repositories.RepositorioProveedor;
import jakarta.validation.Valid;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServiceProveedor {
    @Autowired
    private RepositorioProveedor repo;

    public List<DTOProvedor> getAllusers(){
        List<EntitesProveedor> provider = repo.findAll();
        return provider.stream()
                .map(this::ConvertirAProviderDTO)
                .collect(Collectors.toList());
    }

    public DTOProvedor RegistrarProveedor( DTOProvedor dtoProvedor) {

        if (dtoProvedor == null){
            throw new IllegalArgumentException("Usuario y Correo no debe de ser nulo.");
        }
        try{
            EntitesProveedor entitesProveedor = ConvertirAProviderEntity(dtoProvedor);
            EntitesProveedor providerentity = repo.save(entitesProveedor);
            return ConvertirAProviderDTO(providerentity);
        }catch (Exception e){
            log.error("Error al registrar el Usuario "+ e.getMessage());
            throw new ExceptionUsuarioNoEncontrado("Error al Registrar Usuario" + e.getMessage());
        }
    }

    public DTOProvedor ActualizarProveedores(Long providerID,@Valid DTOProvedor dtoProvider){
        EntitesProveedor entitiesExiste = repo.findById(providerID).orElseThrow(() -> new ExceptionUsuarioNoEncontrado("Proveedor No Encontrado"));

        entitiesExiste.setProviderID(dtoProvider.getProviderID());
        entitiesExiste.setProviderName(dtoProvider.getProviderName());
        entitiesExiste.setProviderPhone(dtoProvider.getProviderPhone());
        entitiesExiste.setProviderAddress(dtoProvider.getProviderAddress());
        entitiesExiste.setEmail(dtoProvider.getEmail());
        entitiesExiste.setProviderCode(dtoProvider.getProviderCode());
        entitiesExiste.setProviderStatus(dtoProvider.getProviderStatus());
        entitiesExiste.setProviderComments(dtoProvider.getProviderComments());

        EntitesProveedor ProviderActualizado = repo.save(entitiesExiste);

        return ConvertirAProviderDTO(ProviderActualizado);
    }









    private EntitesProveedor ConvertirAProviderEntity(DTOProvedor dtoProvedor) {
        EntitesProveedor provider = new EntitesProveedor();
        provider.setProviderName(dtoProvedor.getProviderName());
        provider.setProviderPhone(dtoProvedor.getProviderPhone());
        provider.setProviderAddress(dtoProvedor.getProviderAddress());
        provider.setEmail(dtoProvedor.getEmail());
        provider.setProviderCode(dtoProvedor.getProviderCode());
        provider.setProviderStatus(dtoProvedor.getProviderStatus());
        provider.setProviderComments(dtoProvedor.getProviderComments());

        return provider;
    }


    private DTOProvedor ConvertirAProviderDTO(EntitesProveedor entity) {
        DTOProvedor dto = new DTOProvedor();
        dto.setProviderID(entity.getProviderID());
        dto.setProviderName(entity.getProviderName());
        dto.setProviderPhone(entity.getProviderPhone());
        dto.setProviderAddress(entity.getProviderAddress());
        dto.setEmail(entity.getEmail());
        dto.setProviderCode(entity.getProviderCode());
        dto.setProviderStatus(entity.getProviderStatus());
        dto.setProviderComments(entity.getProviderComments());

        return dto;
    }

    public boolean RemoverUsuario(Long providerid){
        try{
            EntitesProveedor eliminarProvider = repo.findById(providerid).orElse(null);
            if (eliminarProvider != null){
                repo.deleteById(providerid);
                return true;
            }else{
                System.out.println("Usuario No encontrado");
            }
            return false;
        }catch(Exception e){
            throw new EmptyResultDataAccessException("No se pudo eliminar"+ providerid + "Intente otra vez",1);
        }
    }
}
