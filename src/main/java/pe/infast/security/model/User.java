package pe.infast.security.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import pe.infast.security.model.embebed.Rol;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@ToString
public class User {	
	@Id
	private String userId;
	private String personId;
	private String careCenterId;
	@NotBlank
	private String login;
	private String password;
	private List<Rol> roles;
	private int state;
	private String  modifierUser;
	private String  createdUser;

}
