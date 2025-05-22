<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Subject extends Model
{
    use SoftDeletes;

    protected $fillable = [
        'subject_name'
    ];

    public function enrolments(){
        return $this->hasMany(Enrolment::class);
    }

    public function students(){
        return $this->belognsToMany(Student::class, 'enrolments')
                    ->withPivot('status')
                    ->withTimestamps();
    }
}
