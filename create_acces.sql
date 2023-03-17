
select*from AccessMenu


CREATE TABLE AccessMenu
(
	Posisi varchar(50) not null,
	--Menu Home
	HomeView bit not null,
	--menu changepassword
	UpdateAccountChangePassword bit not null,
	--Menu ROOM
	RoomList bit not null,
	RoomUpsert bit not null,
	RoomAdd bit not null,
	RoomUpdate bit not null,
	RoomDelete bit not null,
	RoomDetail bit not null,
	--Menu Customer
	CustomerList bit not null,
	CustomerDelete bit not null,
	CustomerTransaction bit not null,
	CustomerUpdate bit not null,
	--MENU Admin
	AdminList bit not null,
	AdminInsert bit not null,
	AdminAdd bit not null,
	AdminDelete bit not null,
	AdminUpdate bit not null,
	--MENU  RESERVED
	ReservedCurrentReserved bit not null,
	ReservedDelete bit not null,
	ReservedReservation bit not null,
	ReservedBill bit not null,
	ReservedConfirm bit not null,
	ReservMyReserv bit not null
)

select*from AccessMenu

insert into AccessMenu
values('Administrator', 1, 1, 
		1, 1, 1, 1, 1, 1,
		1, 1, 1, 0,
		0, 0, 0, 0, 1,
		1, 1, 0, 0, 0, 0),
		('SuperAdmin', 1, 0, 
		1, 1, 1, 1, 1, 1,
		1, 1, 1, 0,
		1, 1, 1, 1, 0,
		1, 1, 0, 0, 0, 0),
		('Customer', 1, 1, 
		0, 0, 0, 0, 0, 0,
		0, 0, 0, 1,
		0, 0, 0, 0, 0,
		0, 0, 1, 1, 1, 1)

