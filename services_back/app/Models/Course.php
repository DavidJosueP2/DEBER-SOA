<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Enrollment;

class Course extends Model
{
    protected $table = "courses";
    protected $fillable = ["title", "description"];

    public function enrollments()
    {
        return $this->hasMany(Enrollment::class);
    }
}
