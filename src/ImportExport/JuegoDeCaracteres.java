/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImportExport;

import java.util.EnumSet;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author dolf
 */
public enum JuegoDeCaracteres {

    //<editor-fold defaultstate="collapsed" desc="caracteres">
    RC("\\RC", "\r"),
    BR("\\BR", "\n"),
    TB("\\TB", "\t"),
    A1("\\A1", "¡"),
    A2("\\A2", "¢"),
    A3("\\A3", "£"),
    A4("\\A4", "¤"),
    A5("\\A5", "¥"),
    A6("\\A6", "¦"),
    A7("\\A7", "§"),
    A8("\\A8", "¨"),
    A9("\\A9", "©"),
    AA("\\AA", "ª"),
    AB("\\AB", "«"),
    AC("\\AC", "¬"),
    AD("\\AD", "-"),
    AE("\\AE", "®"),
    AF("\\AF", "¯"),
    B0("\\B0", "°"),
    B1("\\B1", "±"),
    B2("\\B2", "²"),
    B3("\\B3", "³"),
    B4("\\B4", "´"),
    B5("\\B5", "µ"),
    B6("\\B6", "¶"),
    B7("\\B7", "·"),
    B8("\\B8", "¸"),
    B9("\\B9", "¹"),
    BA("\\BA", "º"),
    BB("\\BB", "»"),
    BC("\\BC", "¼"),
    BD("\\BD", "½"),
    BE("\\BE", "¾"),
    BF("\\BF", "¿"),
    C0("\\C0", "À"),
    C1("\\C1", "Á"),
    C2("\\C2", "Â"),
    C3("\\C3", "Ã"),
    C4("\\C4", "Ä"),
    C5("\\C5", "Å"),
    C6("\\C6", "Æ"),
    C7("\\C7", "Ç"),
    C8("\\C8", "È"),
    C9("\\C9", "É"),
    CA("\\CA", "Ê"),
    CB("\\CB", "Ë"),
    CC("\\CC", "Ì"),
    CD("\\CD", "Í"),
    CE("\\CE", "Î"),
    CF("\\CF", "Ï"),
    D0("\\D0", "Ð"),
    D1("\\D1", "Ñ"),
    D2("\\D2", "Ò"),
    D3("\\D3", "Ó"),
    D4("\\D4", "Ô"),
    D5("\\D5", "Õ"),
    D6("\\D6", "Ö"),
    D7("\\D7", "×"),
    D8("\\D8", "Ø"),
    D9("\\D9", "Ù"),
    DA("\\DA", "Ú"),
    DB("\\DB", "Û"),
    DC("\\DC", "Ü"),
    DD("\\DD", "Ý"),
    DE("\\DE", "Þ"),
    DF("\\DF", "ß"),
    E0("\\E0", "à"),
    E1("\\E1", "á"),
    E2("\\E2", "â"),
    E3("\\E3", "ã"),
    E4("\\E4", "ä"),
    E5("\\E5", "å"),
    E6("\\E6", "æ"),
    E7("\\E7", "ç"),
    E8("\\E8", "è"),
    E9("\\E9", "é"),
    EA("\\EA", "ê"),
    EB("\\EB", "ë"),
    EC("\\EC", "ì"),
    ED("\\ED", "í"),
    EE("\\EE", "î"),
    EF("\\EF", "ï"),
    F0("\\F0", "ð"),
    F1("\\F1", "ñ"),
    F2("\\F2", "ò"),
    F3("\\F3", "ó"),
    F4("\\F4", "ô"),
    F5("\\F5", "õ"),
    F6("\\F6", "ö"),
    F7("\\F7", "÷"),
    F8("\\F8", "ø"),
    F9("\\F9", "ù"),
    FA("\\FA", "ú"),
    FB("\\FB", "û"),
    FC("\\FC", "ü"),
    FD("\\FD", "ý"),
    FE("\\FE", "þ"),
    FF("\\FF", "ÿ");
    //</editor-fold>

    private String codigo;
    private String valor;

    private JuegoDeCaracteres(String codigo, String valor) {
        this.codigo = codigo;
        this.valor = valor;
    }

    public static String getCodigoPorValor(String valor) {
        Optional<JuegoDeCaracteres> optBuscado = EnumSet
                .allOf(JuegoDeCaracteres.class).stream()
                .filter(x -> x.getValor().equals(valor))
                .findFirst();

        if (!optBuscado.isPresent()) {
            return valor;
        }
        return optBuscado.get().getCodigo();
    }

    public static String getValorPorCodigo(String codigo) {
        Optional<JuegoDeCaracteres> optBuscado = EnumSet
                .allOf(JuegoDeCaracteres.class).stream()
                .filter(x -> x.getCodigo().equals(codigo))
                .findFirst();

        if (!optBuscado.isPresent()) {
            return codigo;
        }
        return optBuscado.get().getValor();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getValor() {
        return valor;
    }

}
