package DiegoZelaya_20240181.DiegoZelaya_20240181.Exceptions;

import lombok.Getter;

public class ExceptionDatosDuplicados extends RuntimeException {

    @Getter
    private String CampoDuplicado;

    public ExceptionDatosDuplicados(String message, String campoDuplicado) {
        super(message);
        this.CampoDuplicado = campoDuplicado;
    }

    public ExceptionDatosDuplicados(String campoDuplicado) {
        this.CampoDuplicado = campoDuplicado;
    }


}
