# audit-firma-simulacion-client-java [![GitHub Packages](https://img.shields.io/badge/Maven&nbsp;package-Last&nbsp;version-lemon)](https://github.com/orgs/APIHub-CdC/packages?repo_name=audit-firma-simulacion-client-java) 

Esta API simula la sustitución de la firma autógrafa para sustentar la autorización por medio de NIP, huella digital o dicio.<br/><img src='https://github.com/APIHub-CdC/imagenes-cdc/blob/master/circulo_de_credito-apihub.png' height='37' width='160'/><br/>

## Requisitos

1. Java >= 1.7
2. Maven >= 3.3

## Instalación

Para la instalación de las dependencias se deberá ejecutar el siguiente comando:

```shell
mvn install -Dmaven.test.skip=true
```

> **NOTA:** Este fragmento del comando *-Dmaven.test.skip=true* evitará que se lance la prueba unitaria.


## Guía de inicio

### Paso 1. Agregar el producto a la aplicación

Al iniciar sesión seguir los siguientes pasos:

 1. Dar clic en la sección "**Mis aplicaciones**".
 2. Seleccionar la aplicación.
 3. Ir a la pestaña de "**Editar '@tuApp**' ".
    <p align="center">
      <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/edit_applications.jpg" width="900">
    </p>
 4. Al abrirse la ventana emergente, seleccionar el producto.
 5. Dar clic en el botón "**Guardar App**":
    <p align="center">
      <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/selected_product.jpg" width="400">
    </p>

### Paso 2. Capturar los datos de la petición

Los siguientes datos a modificar se encuentran en ***src/test/java/com/cdc/apihub/mx/AuditFirma/test/ApiTest.java***

Es importante contar con el setUp() que se encargará de inicializar la petición. Por tanto, se debe modificar la URL (**the_url**); y la API KEY (**your_api_key**), como se muestra en el siguiente fragmento de código:

```java
@Before()
public void setUp() {
    this.xApiKey = "your_api_key";
    this.apiClient = api.getApiClient();
    this.apiClient.setBasePath("the_url");
}
```

La petición se deberá modificar el siguiente fragmento de código con los datos correspondientes:

> **NOTA:** Para más ejemplos de simulación, consulte la colección de Postman.

```java
@Test
public void nipTest() throws ApiException {
    
    SustitucionNIPPeticion peticion = new SustitucionNIPPeticion();
    Persona persona = new Persona();
    Domicilio domicilio = new Domicilio();
    
    Integer estatusOK = 200;
    Integer estatusNoContent = 204;
    
    try {
        
        domicilio.setCalleNumero("AV 535 84");
        domicilio.setColonia("SAN JUAN DE ARAGON 1RA SECC");
        domicilio.setCiudad( "CIUDAD DE MEXICO");
        domicilio.setEstado(CatalogoEstados.CDMX);
        
        persona.setPrimerNombre("NOMBRE");
        persona.setSegundoNombre("SEGUNDONOMBRE");
        persona.setApellidoPaterno("PATERNO");
        persona.setApellidoMaterno("MATERNO");
        persona.setApellidoAdicional(null);
        persona.setRFC("PUAP850316MI1");
        persona.setDomicilio(domicilio);

        peticion.setFolioCDC(763211111);
        peticion.setFechaConsulta("2021/04/15");
        peticion.setHoraConsulta("10/12/35");
        peticion.setTipoConsulta(CatalogoTipoPersona.PF);
        peticion.setUsuario("NGA9915CC5");
        peticion.setFechaAprobacionConsulta("2021/04/15");
        peticion.setHoraAprobacionConsulta("10/12/35");
        peticion.setIngresoNuevamenteNIP(true);
        peticion.setRespuestaLeyendaAutorizacion(true);
        peticion.setAceptaTerminosCondiciones(true);
        peticion.setNumeroFirma("1234F");
        peticion.setPersona(persona);
        
        ApiResponse<?>  response = api.genericNIP(this.xApiKey, peticion);
        
        Assert.assertTrue(estatusOK.equals(response.getStatusCode()));
        
        if(estatusOK.equals(response.getStatusCode())) {
            SustitucionNIPRespuesta responseOK = (SustitucionNIPRespuesta) response.getData();
            logger.info(responseOK.toString());
        }

    } catch (ApiException e) {
        if(!estatusNoContent.equals(e.getCode())) {
            logger.info(e.getResponseBody());
        }
        Assert.assertTrue(estatusOK.equals(e.getCode()));           
    }
}
```

### Paso 3. Ejecutar la prueba unitaria

Teniendo los pasos anteriores ya solo falta ejecutar la prueba unitaria, con el siguiente comando:

```shell
mvn test -Dmaven.install.skip=true
```

---
[CONDICIONES DE USO, REPRODUCCIÓN Y DISTRIBUCIÓN](https://github.com/APIHub-CdC/licencias-cdc)

[1]: https://getcomposer.org/doc/00-intro.md#installation-linux-unix-macos
