package ar.edu.iua.business;

import java.util.List;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.User;

public interface IUserBusiness {
	
		//-----Cargamos un usuario-----
		public User load(Integer id) throws NotFoundException, BusinessException;
		
		//-----Listamos los usuarios-----
		public List<User> list() throws BusinessException;
		
		//----Guardar un Usuario------
		public User add(User user) throws BusinessException;
		
		//----Editar un Usuario------
		public User update(User user) throws NotFoundException,BusinessException;
		
		//-----Cargamos un usuario por mail-----
		public User load(String nameOrEmail) throws NotFoundException, BusinessException;
		
}
