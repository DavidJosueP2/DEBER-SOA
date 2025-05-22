<?php

namespace App\Http\Controllers;

use App\Models\Enrolment;
use App\Models\Student;
use App\Models\Subject;
use Illuminate\Http\Request;

class EnrolmentController extends Controller
{
    public function index(Request $request)
    {
        $students = Student::all();
        $subjects = Subject::all();

        $query = Enrolment::with(['student', 'subject']);

        if ($request->filled('student_id')) {
            $query->where('student_id', $request->student_id);
        }

        if ($request->filled('subject_id')) {
            $query->where('subject_id', $request->subject_id);
        }

        $enrolments = $query->get();

        return view('enrolments.index', compact('enrolments', 'students', 'subjects'));
    }  

    public function store(Request $request)
    {
        $request->validate([
            'student_id' => 'required|exists:students,id',
            'subject_id' => 'required|exists:subjects,id',
        ]);

        $exists = Enrolment::where('student_id', $request->student_id)
                           ->where('subject_id', $request->subject_id)
                           ->first();
        
        if ($exists) {
            return redirect()->route('enrolments.index')->with('error', 'Ya existe esta matrícula.');
        }

        Enrolment::create([
            'student_id' => $request->student_id,
            'subject_id' => $request->subject_id,
            'status' => 'active',
        ]);
        
        return redirect()->route('enrolments.index')->with('success', 'Matrícula creada correctamente.');
    }

    public function store(Request $request)
    {
        $request->validate([
            'student_id' => 'required|exists:students,id',
            'subject_id' => 'required|exists:subjects,id'
        ]);
        
        $exists = Enrolment::withTrashed()
            ->where('student_id', $request->student_id)
            ->where('subject_id', $request->subject_id)
            ->first();

        if ($exists) {
            if ($exists->trashed()) {
                $exists->restore();
                return redirect()->route('enrolments.index')->with('success', 'Matrícula restaurada correctamente.');
            }
            return redirect()->route('enrolments.index')->with('error', 'Ya existe esta matrícula.');
        }
        
        Enrolment::create([
            'student_id' => $request->student_id,
            'subject_id' => $request->subject_id,
            'status' => 'activo',
        ]);

        return redirect()->route('enrolments.index')->with('success', 'Matrícula creada correctamente.');
    }

    public function update(Request $request, Enrolment $enrolment)
    {
        $request->validate([
            'student_id' => 'required|exists:students,id',
            'subject_id' => 'required|exists:subjects,id',
        ]);

        $exists = Enrolment::where('student_id', $request->student_id)
            ->where('subject_id', $request->subject_id)
            ->where('id', '!=', $enrolment->id)
            ->first();
        
        if ($exists) {
            return redirect()->route('enrolments.index')->with('error', 'Ya existe esta matrícula.');
        }
        
        $enrolment->update([
            'student_id' => $request->student_id,
            'subject_id' => $request->subject_id,
        ]);
        
        return redirect()->route('enrolments.index')->with('success', 'Matrícula actualizada correctamente.');
    } 

    public function destroy(Enrolment $enrolment)
    {
        $enrolment->delete();
            
        return redirect()->route('enrolments.index')->with('success', 'Matrícula eliminada.');
    }
}
