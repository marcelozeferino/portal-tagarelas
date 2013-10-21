CREATE  VIEW `mensagensporminuto` AS 
  select 
    `mensagem`.`idAgenda` AS `idAgenda`,
    concat(lpad(hour(`mensagem`.`horario`),2,'0'),':',lpad(minute(`mensagem`.`horario`),2,'0')) AS `horario`,
    count(0) AS `total` 
  from 
    `mensagem` 
  group by 
    `mensagem`.`idAgenda`,concat(lpad(hour(`mensagem`.`horario`),2,'0'),':',lpad(minute(`mensagem`.`horario`),2,'0')) 
  order by 
    1;

CREATE  VIEW `mensagensporminutousuario` AS 
  select 
    `m`.`idAgenda` AS `idAgenda`,
    `u`.`nomeUsuario` AS `nomeUsuario`,
    concat(lpad(hour(`m`.`horario`),2,'0'),':',lpad(minute(`m`.`horario`),2,'0')) AS `horario`,
    count(0) AS `total` 
  from 
    (`mensagem` `m` left join `usuario` `u` on((`m`.`idUsuario` = `u`.`idUsuario`))) 
  group by 
    `m`.`idAgenda`,`u`.`nomeUsuario`,concat(lpad(hour(`m`.`horario`),2,'0'),':',lpad(minute(`m`.`horario`),2,'0')) 
  order by 
    1,2;

CREATE VIEW `mensagesporusuario` AS 
  select 
    `m`.`idAgenda` AS `idAgenda`,
    `u`.`nomeUsuario` AS `nomeUsuario`,
    count(0) AS `totalMensagens`,
    sum(length(`m`.`conteudo`)) AS `totalCaracteres` 
  from 
    (`mensagem` `m` join `usuario` `u`) 
  where 
    (`m`.`idUsuario` = `u`.`idUsuario`) 
  group by 
    `u`.`nomeUsuario`,`m`.`idAgenda` 
  order by 
    count(0) desc;