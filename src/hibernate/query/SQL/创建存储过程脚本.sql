/* DbVisualizer �д����洢������Ҫ "--/"��"/" ����ͷ�ͽ�β */
--/ 
create procedure getDogByName(IN aname varchar(20))
begin
  select * from tb_dog where name=aname;
end
/

--/ 
create procedure deleteDog(IN id varchar(20))
begin
  delete from tb_dog where pk_dog=id;
end
/