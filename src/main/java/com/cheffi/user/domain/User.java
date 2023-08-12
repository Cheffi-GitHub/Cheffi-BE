package com.cheffi.user.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.common.domain.BaseTimeEntity;
import com.cheffi.user.constant.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String email;

	@NotNull
	private boolean locked;

	@NotNull
	private boolean expired;
	@NotNull
	private boolean withdrawn;
	private LocalDateTime withdrawnDate;
	@NotNull
	private boolean activated;
	@NotNull
	private LocalDateTime lastPwChangedDate;
	@NotNull
	private String name;

	@NotNull
	private UserType userType;
	@NotNull
	private boolean adAgreed;
	@NotNull
	private boolean analysisAgreed;
	private String pwHash;
	private String pwSalt;
	private String fcmToken;

	@OneToOne(mappedBy = "user")
	private Avatar avatar;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserRole> userRoles = new ArrayList<>();

	@Builder
	private User(String email, boolean locked, boolean expired, boolean withdrawn, boolean activated, String name,
		UserType userType) {
		this.email = email;
		this.locked = locked;
		this.expired = expired;
		this.withdrawn = withdrawn;
		this.activated = activated;
		this.name = name;
		this.userType = userType;
	}

	public void addRoles(List<Role> roles) {
		roles.stream().filter(r ->
				userRoles.stream().map(UserRole::getRole).noneMatch(ar -> ar.equals(r)))
			.map(r -> UserRole.mapRoleToUser(this, r))
			.forEach(userRoles::add);
	}

	public static User createUser(String email, String name, UserType userType, List<Role> roles) {
		Assert.hasText(email,"이메일이 입력되지 않았습니다.");
		Assert.hasText(name,"이름이 입력되지 않았습니다");
		Assert.notNull(userType,"유저타입은 null일 수 없습니다.");
		Assert.notEmpty(roles,"유저 권한(Role)은 최소한 1개는 부여되어야 합니다.");

		User createdUser = User.builder()
			.name(email)
			.email(name)
			.userType(userType)
			.locked(false)
			.expired(false)
			.withdrawn(false)
			.activated(true)
			.build();
		createdUser.addRoles(roles);
		return createdUser;
	}

}
