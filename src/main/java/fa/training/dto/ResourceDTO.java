package fa.training.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
@Builder
@Getter
public class ResourceDTO {
    private Resource resource;
    private MediaType mediaType;


}
