INSERT IGNORE INTO permiso(id, nombre, maps, caracterizacion, fallo, dashboard) VALUES (1, "ADMIN", 1,1,1,1),
                                             (2, "CONSULTA", 1,0,0,1),
                                             (3, "CARACT", 1,1,0,0),
                                             (4, "CUADRILLA", 1,0,1,0);

INSERT IGNORE INTO perfiles(id, rol_nombre, permiso_id) VALUES (1, "ADMIN", 1),
                                             (2, "CONSULTA", 2),
                                             (3, "CARACT", 3),
                                             (4, "CUADRILLA", 4);

INSERT IGNORE INTO tipo_punto(id, tipo_nombre) VALUES (1,"ESTACION"),(2,"POSTE");
