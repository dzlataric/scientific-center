package com.scientific.center.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode
@Builder(toBuilder = true)
@Table(name = "T_USER")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USERNAME", nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "COUNTRY", nullable = false)
	private String country;

	@Column(name = "CITY", nullable = false)
	private String city;

	@Column(name = "EMAIL_ADDRESS", nullable = false)
	private String emailAddress;

	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE")
	private Role role;

	@Column(name = "REVIEWER")
	private Boolean reviewer;

	@Column(name = "ENABLED")
	private boolean enabled;

	@Column(name = "ENABLED_AS_REVIEWER")
	private boolean enabledAsReviewer;

	@ManyToMany
	private Set<AreaOfScienceEntity> areasOfScience;

}
