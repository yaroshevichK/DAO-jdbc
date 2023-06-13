package it.academy.mapper;

import javax.servlet.http.HttpServletRequest;

/**
 * @param <TEntity> entity (database layer)
 * @param <TDto>    entity in client layer
 * @author Katerina
 * @version 1.0
 */
public interface Mapper<TEntity, TDto> {
    /**
     * Convert dto object in TEntity object.
     *
     * @param dto object in client layer
     * @return entity object
     */
    TEntity dtoToEntity(TDto dto);

    /**
     * Convert TEntity object in dto object.
     *
     * @param entity object in database layer
     * @return dto object (client layer)
     */
    TDto entityToDto(TEntity entity);

    /**
     * Convert request parameters in dto object.
     *
     * @param request servlet request
     * @return dto object (client layer)
     */
    TDto requestToDto(HttpServletRequest request);
}
