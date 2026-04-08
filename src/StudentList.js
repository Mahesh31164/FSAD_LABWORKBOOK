import React, { useEffect, useState } from "react";
import axios from "axios";

function StudentList({ setSelected }) {
  const [students, setStudents] = useState([]);

  const load = () => {
    axios.get("http://localhost:8080/students")
      .then(res => setStudents(res.data));
  };

  useEffect(() => {
    load();
  }, []);

  const deleteStudent = (id) => {
    axios.delete(`http://localhost:8080/students/${id}`)
      .then(load);
  };

  return (
    <div>
      <h2>Student List</h2>
      {students.map(s => (
        <div key={s.id}>
          {s.name} - {s.email} - {s.course}
          <button onClick={() => setSelected(s)}>Edit</button>
          <button onClick={() => deleteStudent(s.id)}>Delete</button>
        </div>
      ))}
    </div>
  );
}

export default StudentList;