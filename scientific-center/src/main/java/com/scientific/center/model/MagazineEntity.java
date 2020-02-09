package com.scientific.center.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "T_MAGAZINE")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MagazineEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "ISSN", nullable = false)
	private Integer issn;

	@Enumerated(EnumType.STRING)
	@Column(name = "MEMBERSHIP_FEE_TYPE")
	private MembershipFeeType membershipFeeType;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
		name = "T_MAGAZINE_AREA_OF_SCIENCE",
		joinColumns = { @JoinColumn(name = "MAGAZINE_ID") },
		inverseJoinColumns = { @JoinColumn(name = "AREA_OF_SCIENCE_ID") }
	)
	List<AreaOfScienceEntity> areasOfScience;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
		name = "T_MAGAZINE_PAYMENT_TYPE",
		joinColumns = { @JoinColumn(name = "MAGAZINE_ID") },
		inverseJoinColumns = { @JoinColumn(name = "PAYMENT_TYPE_ID") }
	)
	List<PaymentTypeEntity> paymentTypes;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
		name = "T_MAGAZINE_REVIEWER",
		joinColumns = { @JoinColumn(name = "MAGAZINE_ID") },
		inverseJoinColumns = { @JoinColumn(name = "REVIEWER_ID") }
	)
	List<UserEntity> reviewers;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
		name = "T_MAGAZINE_EDITOR",
		joinColumns = { @JoinColumn(name = "MAGAZINE_ID") },
		inverseJoinColumns = { @JoinColumn(name = "EDITOR_ID") }
	)
	List<UserEntity> editors;

}
