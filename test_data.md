# Test Data para ItemMaterial en ListaMateriales

## 1. Crear Rol (POST /api/roles)
```json
{
    "nombre": "ADMIN",
    "descripcion": "Administrador del sistema"
}
```

## 2. Crear Usuario (POST /api/usuarios)
```json
{
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan@test.com",
    "dni": "12345678",
    "password": "password123",
    "rolId": 1
}
```

## 3. Crear Materiales (POST /api/material)
```json
{
    "nombre": "Cemento",
    "descripcion": "Cemento Portland",
    "serie": "CEM001",
    "unidades": 100,
    "costoUnitario": 25.50
}
```

```json
{
    "nombre": "Arena",
    "descripcion": "Arena fina para construcción",
    "serie": "ARE001",
    "unidades": 50,
    "costoUnitario": 15.00
}
```

```json
{
    "nombre": "Ladrillos",
    "descripcion": "Ladrillos rojos",
    "serie": "LAD001",
    "unidades": 200,
    "costoUnitario": 0.50
}
```

## 4. Crear Lista de Materiales (POST /api/lista-materiales/crear)
```json
{
    "nombre": "Lista Principal",
    "descripcion": "Lista de materiales principal",
    "usuarioId": 1,
    "items": [
        {
            "materialId": 1,
            "cantidad": 5,
            "observaciones": "Cemento para cimientos"
        }
    ]
}
```

## 5. Agregar Item a Lista Existente (POST /api/lista-materiales/1/items)
```json
{
    "materialId": 2,
    "cantidad": 10,
    "observaciones": "Arena para mezcla"
}
```

## 6. Agregar otro Item (POST /api/lista-materiales/1/items)
```json
{
    "materialId": 3,
    "cantidad": 20,
    "observaciones": "Ladrillos para paredes"
}
```

## 7. Ver items de la lista (GET /api/lista-materiales/1/items)

## 8. Ver lista completa (GET /api/lista-materiales/1)
