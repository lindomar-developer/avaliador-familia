package br.com.digix.avaliadorfamilia.helper;

import br.com.digix.avaliadorfamilia.enums.MensagemEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

import javax.annotation.PostConstruct;
import java.util.Locale;

@RequiredArgsConstructor
public class MensagensComum {

    private final MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    public void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    public String getMensagem(String chave, Object... valores) {
        return accessor.getMessage(chave, valores);
    }

    public String getMensagem(MensagemEnum mensagemEnum, Object... valores) {
        return accessor.getMessage(mensagemEnum.getChaveMsg(), valores);
    }

}
