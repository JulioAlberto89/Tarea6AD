CREATE DATABASE IF NOT EXISTS `tarea6` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `tarea6`;

-- --------------------------------------------------------

CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
    `nombre` varchar(25) DEFAULT NULL,
  `usuario` varchar(25) DEFAULT NULL,
   `clave` varchar(40) DEFAULT NULL
  
 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `usuario`
  ADD PRIMARY KEY (`IDUSUARIO`);

  ALTER TABLE `usuario`
  MODIFY `IDUSUARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
  
--
-- Estructura de tabla para la tabla `medico`
--

CREATE TABLE `medico` (
  `id_medico` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `sala` decimal(4,0) DEFAULT NULL,
  `especialidad` varchar(30) DEFAULT NULL,
  `tarifa` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Indices de la tabla `medico`
--
ALTER TABLE `medico`
  ADD PRIMARY KEY (`id_medico`);
  
  --
-- AUTO_INCREMENT de la tabla `medico`
--
ALTER TABLE `medico`
  MODIFY `id_medico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Volcado de datos para la tabla `medico`
--

INSERT INTO `medico` (`id_medico`, `nombre`, `sala`, `especialidad`, `tarifa`) VALUES
(2, 'Javier', '14', 'Urologo', 90),
(3, 'Manuel2', '14', 'trauma', 80),
(4, 'Rafael Lenzano', '24', 'Digestivo', 120);