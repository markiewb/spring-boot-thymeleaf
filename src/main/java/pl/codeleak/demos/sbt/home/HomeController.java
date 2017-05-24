package pl.codeleak.demos.sbt.home;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
class HomeController {

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("modelAttribute_address", "Earth");
        return "index";
    }

    @GetMapping("properties")
    @ResponseBody
    java.util.Properties properties() {
        return System.getProperties();
    }

    /**
     * Aufruf mit
     * <pre>
     *     java -jar meinJar.jar --userid=foo
     * </pre>
     *
     * @param userid
     * @return
     */
    @Bean(name = "messageSource")
    public MessageSource messageSource(@Value("${userid}") String userid) {
        final ResourceBundleMessageSource messageSource;

        messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename(String.format("users/%s/messages", userid));

        return messageSource;
    }
}
