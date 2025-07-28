package DiegoZelaya_20240181.DiegoZelaya_20240181.Controller;

import DiegoZelaya_20240181.DiegoZelaya_20240181.Exceptions.ExceptionDatosDuplicados;
import DiegoZelaya_20240181.DiegoZelaya_20240181.Exceptions.ExceptionUsuarioNoEncontrado;
import DiegoZelaya_20240181.DiegoZelaya_20240181.Models.DTO.DTOProvedor;
import DiegoZelaya_20240181.DiegoZelaya_20240181.Service.ServiceProveedor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Api")
public class ControllerProveedor {

    @Autowired
    private ServiceProveedor service;

    @GetMapping("/ObtenerProveedor")
    public List<DTOProvedor> datosUsuarios(){
        return service.getAllusers();
    }

    @PostMapping("/InsertarProveedores")
    public ResponseEntity<Map<String,Object>> registrarProvider(@Valid @RequestBody DTOProvedor dtoProvedor, HttpServletRequest request){
        try{
            DTOProvedor respuesta = service.RegistrarProveedor(dtoProvedor);
            if (respuesta == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "Status", "Insercion Incorrecta (Que nuv)",
                        "errorType", "VALIDATION_ERROR_INSERT",
                        "message", "Datos del usuario Invalidos"
                ));

            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
               "Status", "Insercion Correcta (Que pro)",
               "Data", respuesta));

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "Status", "Error (Que nuv)",
                            "message", "Error al regsitar el Proveedor",
                            "detail", e.getMessage()
                    ));
        }
    }

    @PutMapping("/ActualizarProveedor/{providerid}")
    public ResponseEntity<?> ModificarProveedor(
            @PathVariable Long providerid,
            @Valid @RequestBody DTOProvedor dtoProvedor,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errores);
        }
        try{
            DTOProvedor proveedoresPut = service.ActualizarProveedores(providerid, dtoProvedor);
            return ResponseEntity.ok(proveedoresPut);
        }
        catch(ExceptionDatosDuplicados e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "Error", "Datos Duplicados", "Campos", e.getCampoDuplicado()
            ));
        }
        catch(ExceptionUsuarioNoEncontrado e){
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/Eliminar/{providerid}")
    public ResponseEntity<Map<String, Object>> eliminarProvider(@PathVariable Long providerid){
        try{
            if(!service.RemoverUsuario(providerid)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("Error", "No se Pudo Eliminar (nuv)")
                        .body(Map.of());
            }
            return  ResponseEntity.ok().body(Map.of());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of());
        }
    }



    @GetMapping("/BuscarProveedor/{providerid}")
    public ResponseEntity<DTOProvedor> obtenerporId(@PathVariable Long providerid){
        DTOProvedor provedor = service.ObtneporID(providerid);
        return ResponseEntity.ok(provedor);
    }

}
