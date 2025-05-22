<?php

use App\Http\Controllers\CourseController;
use App\Http\Controllers\EnrollmentController;
use App\Http\Controllers\StudentController;
use Illuminate\Support\Facades\Route;

Route::get("/students", [StudentController::class, 'index']);
Route::get("/students/{id}", [StudentController::class, 'show']);
Route::get("/students/withEnrollments/{id}", [StudentController::class, 'withEnrollments']);
Route::post("/students", [StudentController::class, 'save']);
Route::put("/students/{id}", [StudentController::class, 'update']);
Route::delete("/students/{id}", [StudentController::class, 'delete']);

Route::get("/courses", [CourseController::class, 'index']);
Route::get("/courses/{id}", [CourseController::class, 'show']);
Route::get("/courses/withEnrollments/{id}", [CourseController::class, 'withEnrollments']);
Route::post("/courses", [CourseController::class, 'save']);
Route::put("/courses/{id}", [CourseController::class, 'update']);
Route::delete("/courses/{id}", [CourseController::class, 'delete']);

Route::get('/enrollments', [EnrollmentController::class, 'index']);
Route::get("/enrollments/{id}", [EnrollmentController::class, 'show']);
Route::post('/enrollments', [EnrollmentController::class, 'save']);
Route::put('/enrollments/{id}', [EnrollmentController::class, 'update']);
Route::delete('/enrollments/{id}', [EnrollmentController::class, 'delete']);
