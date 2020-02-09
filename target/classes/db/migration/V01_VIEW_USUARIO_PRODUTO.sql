CREATE VIEW usuarioAndProduto
AS SELECT u.id AS 'id_usuario', u.user_name, u.senha,
	p.id, p.nome_prod, p.descricao, p.quantidade, p.valor
FROM usuario u
INNER JOIN produto p ON p.id_usuario = u.id;
