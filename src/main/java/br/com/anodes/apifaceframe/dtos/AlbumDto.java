package br.com.anodes.apifaceframe.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDto {

    private Long id;
    private Long userId;
    private Set<Long> photosId;
}
