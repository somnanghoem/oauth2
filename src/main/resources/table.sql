------ USER TOKEN INFO  ---------
create table ouath2_token (
  username varchar(50)	 not null default '',
  token varchar(1000)	 not null default '',
  issueddate			 varchar(8) not null default '',
  issuedtime			 varchar(6) not null default '',
  expireddate			 varchar(8) not null default '',
  expiredtime 			 varchar(6) not null default '',
  remoteip				 varchar(255) not null default '',
  status				 varchar(1) not null default '',
  usertype				 varchar(2) default '',
  primary key ( username )
)

------- USER INFO ----------
create table user_info(
	user_name 				varchar(20) 	not null, -- user name for login
	user_type 				varchar(2) 		not null default ''::character varying, -- 01: mobile, 02: web
	user_status 			varchar(2) 		not null default ''::character varying, -- 00: normal, 01: deleted, 99: deleted
	user_password 			varchar( 255) 	not null default ''::character varying,
	password_change_date 	varchar(8) 		not null default ''::character varying,
	password_change_time 	varchar(9) 		not null default ''::character varying,
	first_login_date 		varchar(8) 		not null default ''::character varying,
	first_login_time 		varchar(9) 		not null default ''::character varying,
	last_login_date 		varchar(8) 		not null default ''::character varying,
	last_login_time 		varchar(9) 		not null default ''::character varying,
	master_user_name 		varchar(20) 	not null default ''::character varying,
	employee_id				varchar(20) 	not null default ''::character varying, -- reserve column
	create_by				varchar(50)		not null, -- user that create
	register_date 			varchar(8) 		not null default ''::character varying,
	register_time 			varchar(9) 		not null default ''::character varying,
	update_date 			varchar(8) 		not null default ''::character varying,
	update_time 			varchar(9) 		not null default ''::character varying,
	update_by				varchar(50) 	not null, -- user that update
	constraint pk_user_info primary key (user_name)
)

--------------------User Request Error Log --------------------------
create table user_error_log(
		user_name 			varchar(20) 	not null default ''::character varying,
		user_type 			varchar(2) 		not null default ''::character varying, -- 01: mobile, 02: web
		register_date 		varchar(8) 		not null default ''::character varying,
		register_time 		varchar(9) 		not null default ''::character varying,
		url					varchar(255) 	not null default ''::character varying,
		ip_address 			varchar(50) 	not null default ''::character varying,
		device_name 		varchar(255) 	not null default ''::character varying,
		error_code 			varchar(255) 	not null default ''::character varying,
		error_description 	varchar(1000) 	not null default ''::character varying,
		error_cause 		text			not null default ''::character varying,
		primary key( user_name,user_type,register_date,register_time)
)

--------------------User access API --------------------------
 CREATE TABLE USER_ACCESS_API(
	user_name 				varchar(20) 	not null, -- user name for login
	user_type 				varchar(2) 		not null default ''::character varying, -- 01: mobile, 02: web
	uri 					varchar(100) 	not null default ''::character varying,
 	uri_description			varchar(255) 	not null default ''::character varying,
 	access_yn				varchar(1) 		not null default ''::character varying,
 	create_by				varchar(50)		not null, -- user that create
	register_date 			varchar(8) 		not null default ''::character varying,
	register_time 			varchar(9) 		not null default ''::character varying,
	update_date 			varchar(8) 		not null default ''::character varying,
	update_time 			varchar(9) 		not null default ''::character varying,
	update_by				varchar(50) 	not null, -- user that update
	CONSTRAINT PK_USER_ACCESS_API PRIMARY KEY (USER_NAME,USER_TYPE,URI)
)
