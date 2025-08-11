INSERT INTO perfiles (nombre) VALUES ('USUARIO');

INSERT INTO usuarios (correo_electronico, contrasena) VALUES ('test@email.com', '$2a$10$tJ92wzK2oF3z.0Q35jH74uUu.WlO5.z8/Jt.gB7uM7.gE8f7k5');

INSERT INTO usuarios_perfiles (usuario_id, perfil_id) VALUES (
    (SELECT id FROM usuarios WHERE correo_electronico = 'test@email.com'),
    (SELECT id FROM perfiles WHERE nombre = 'USUARIO')
);