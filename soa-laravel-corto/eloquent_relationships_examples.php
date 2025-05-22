<?php

use App\Models\Enrolment;
use App\Models\Student;
use App\Models\Subject;

// --------------------
// Ejemplos de relaciones en Eloquent (Laravel)
// --------------------

// 1. hasMany(): Una entidad tiene muchas otras (1:N)
// Ejemplo: Un estudiante tiene muchas matrículas
public function enrolments()
{
    return $this->hasMany(Enrolment::class);
}

// 2. belongsTo(): Una entidad pertenece a otra (N:1)
// Ejemplo: Una matrícula pertenece a un estudiante
public function student()
{
    return $this->belongsTo(Student::class);
}

// Ejemplo adicional: Una matrícula pertenece a una materia
public function subject()
{
    return $this->belongsTo(Subject::class);
}

// 3. hasOne(): Una entidad tiene exactamente una relacionada (1:1)
// Ejemplo (hipotético): Un estudiante tiene un perfil asociado
public function profile()
{
    return $this->hasOne(Profile::class);
}

// 4. belongsToMany(): Relación muchos a muchos (N:N)
// Requiere una tabla intermedia (pivot), como enrolments entre students y subjects
public function subjects()
{
    return $this->belongsToMany(Subject::class, 'enrolments')
                ->withPivot('status')
                ->withTimestamps();
}

// 5. morphOne(): Relación polimórfica uno a uno
// Ejemplo: Una foto puede pertenecer a un estudiante o a un profesor
public function image()
{
    return $this->morphOne(Image::class, 'imageable');
}

// 6. morphMany(): Relación polimórfica uno a muchos
// Ejemplo: Varias fotos asociadas a distintos modelos
public function images()
{
    return $this->morphMany(Image::class, 'imageable');
}

// 7. morphTo(): Relación inversa polimórfica
// Ejemplo: Una imagen puede pertenecer a cualquier modelo
public function imageable()
{
    return $this->morphTo();
}

// 8. hasManyThrough(): Relación indirecta a través de otro modelo
// Ejemplo: Un país tiene muchos comentarios a través de usuarios
public function comments()
{
    return $this->hasManyThrough(Comment::class, User::class);
}

?>
