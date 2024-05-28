package ar.edu.utn.frbb.tup.model;

public enum TipoPersona {

    INDIVIDUAL("F"),
    LEGAL_ENTITY("J");

    private final String description;

    TipoPersona(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static TipoPersona fromString(String text) {
        for (TipoPersona type : TipoPersona.values()) {
            if (type.description.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No se pudo encontrar un TipoPersona con la descripción: " + text);
    }
}
