package api.rest.pais.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import api.rest.pais.domain.Pais;
import api.rest.pais.dto.PaisDTO;

@Mapper(componentModel = "spring")
public interface PaisMapper {
	
	default List<PaisDTO> toResponseList(List<Pais> paises){		
		if(paises == null) {
			return new ArrayList<PaisDTO>();
		}
		
		return paises.stream().map(this::entityToDTO).collect(Collectors.toList());
	}
	
	@Mappings({
		@Mapping(target="codigo", source="entity.isoName"),
		@Mapping(target="nombre", source="entity.name"),
		@Mapping(target="moneda", source="entity.currencyName"),
		@Mapping(target="simbolo", source="entity.currencySymbol"),
		@Mapping(target="bandera", source="entity.flag")
	})
	public PaisDTO entityToDTO(Pais entity);
	
}
