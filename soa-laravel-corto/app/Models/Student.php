<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Student extends Model
{
    use SoftDeletes;

    protected $fillable = [
        'cedula',
        'name',
        'lastname',
        'address',
        'telephone',
    ];

    public function enrolmetns() {
        return $this->hasMany(Enrolment::class);
    }

    public function subjects() {
        return $this->belongsToMany(Subject::class, 'enrolments')
                    ->withPivot('status')
                    ->withTimestamps();
    }
}
