USE ProjetFinale107Db
GO

if OBJECT_ID('Livres','U') is not null
	drop table Categorie;
Go

create table Categorie(
	id int primary key,
	categorie varchar(100),
	titre varchar(100),
	auteur varchar(100),
	prix float,
	img varchar(250),
	quantite int

);

CREATE TABLE dbo.Panier
(
	[Id] INT NOT NULL PRIMARY KEY, 
    [titre] NVARCHAR(50) NULL, 
    [prix] FLOAT NULL, 
    [quantite] INT NULL, 
    [img] NVARCHAR(50) NULL

);
go

DECLARE @Livres varchar(max)

SELECT @Livres =
	BulkColumn
	FROM OPENROWSET (BULK 'd:/Livres.json', SINGLE_CLOB) JSON
	 
--select @Livres as EM_Livres;

if (ISJSON(@Livres)=1)
	begin 
		print 'loading file is valid';

		insert into dbo.Categorie
		select *
		from openjson (@Livres, '$.Livres.litterature')
		with(
			id int '$.id',
			categorie varchar(100) '$.categorie',
			titre varchar(100) '$.titre',
			auteur varchar(100) '$.auteur',
			prix float '$.prix',
			img varchar(250) '$.img',
			quantite int '$.quantite'
		)

		insert into dbo.Livres
		select *
		from openjson (@Livres, '$.Livres.informatique')
		with(
			id int '$.id',
			categorie varchar(100) '$.categorie',
			titre varchar(100) '$.titre',
			auteur varchar(100) '$.auteur',
			prix float '$.prix',
			img varchar(250) '$.img',
			quantite int '$.quantite'
		)
	end
else
	begin 
		print 'loading file is invalid';
	end


select * from dbo.Livres