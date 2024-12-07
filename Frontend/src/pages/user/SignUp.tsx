
import { registerUser } from "../../api/services/admin/accountService";
import { useState, ChangeEvent } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import "react-toastify/dist/ReactToastify.css";
interface User{
  username: string,
  pass: string,
  email: string,
  fullName: string,
  phoneNumber: string
}
export default function SignUp() {
  const [signUpForm, setSignUpForm] = useState<SignUpForm>({
    username: '',
    fullName: '',
    email: '',
    pass: '',
    phoneNumber: '',
  });

  const [confirmPassword, setConfirmPassword] = useState<string>('');
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string>('');

  const navigate = useNavigate();

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setSignUpForm({
      ...signUpForm,
      [name]: value,
    });
  };

  const handleConfirmPasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setConfirmPassword(e.target.value);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (signUpForm.pass !== confirmPassword) {
      setError('Mật khẩu và mật khẩu xác nhận không khớp');
      return;
    }

    setLoading(true);
    try {
      const res = await register(signUpForm);

      if (res.status !== 'success') {
        setError(res.message.mess);
        return;
      }

      if (res.status === 'success') {
        setError('');
        setLoading(false);
        navigate('/dang-nhap');
      }
    } catch (error) {
      setError('Có lỗi xảy ra trong quá trình đăng ký của bạn');
    }
  };

  return (
    <section className="bg-gray-50">
      <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto">
        <div className="w-full bg-white rounded-lg shadow md:mt-0 sm:max-w-md xl:p-0">
          <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
            <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
              Đăng ký
            </h1>
            <form className="space-y-4 md:space-y-6" action="#">
              <div>
                <label
                  htmlFor="username"
                  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                  Tên tài khoản
                </label>
                <input
                  name="username"
                  value={formData.username || ''}
                  onChange={handleChange}
                  placeholder="Nhập tên tài khoản"
                  className="bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-teal-600 focus:border-teal-600 block w-full p-2.5 "
                  required
                />
              </div>
              <div>
                <label
                  htmlFor="pass"
                  className="block mb-2 text-sm font-medium text-gray-900 "
                >
                  Mật khẩu
                </label>
                <input
                  type="password"
                  name="pass"
                  id="pass"
                  placeholder="••••••••"
                  value={formData.pass || ''}
                  onChange={handleChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-teal-600 focus:border-teal-600 block w-full p-2.5"
                  required
                />
              </div>
              <div>
                <label
                  htmlFor="repeatPass"
                  className="block mb-2 text-sm font-medium text-gray-900 "
                >
                  Nhập lại Mật khẩu
                </label>
                <input
                  type="password"
                  name="repeatPass"
                  id="repeatPass"
                  placeholder="••••••••"
                  value={repeatPass}
                  onChange={handleChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-teal-600 focus:border-teal-600 block w-full p-2.5"
                  required
                />
              </div>
            <form className="space-y-4 md:space-y-6" action="#">
              <div>
                <label
                  htmlFor="fullName"
                  className="block mb-2 text-sm font-medium text-gray-900 "
                >
                  Họ và tên
                </label>
                <input
                  type="name"
                  name="fullName"
                  id="fullName"
                  placeholder="nhập họ và tên"
                  value={formData.fullName || ''}
                  onChange={handleChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-teal-600 focus:border-teal-600 block w-full p-2.5"
                  required
                />
              </div>
              <div>
                <label
                  htmlFor="email"
                  className="block mb-2 text-sm font-medium text-gray-900 "
                >
                  Email
                  Họ và tên
                </label>
                <input
                  type="email"
                  name="email"
                  id="email"
                  placeholder="nhập email"
                  value={formData.email || ''}
                  onChange={handleChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-teal-600 focus:border-teal-600 block w-full p-2.5"
                  type="name"
                  name="fullName"
                  id="fullName"
                  placeholder="nhập họ và tên"
                  value={formData.fullName || ''}
                  onChange={handleChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-teal-600 focus:border-teal-600 block w-full p-2.5"
                  required
                />
              </div>
              <div>
                <label
                  htmlFor="phoneNumber"
                  htmlFor="email"
                  className="block mb-2 text-sm font-medium text-gray-900 "
                >
                  Số điện thoại
                </label>
                <input
                  type="email"
                  name="email"
                  id="email"
                  placeholder="nhập email"
                  value={formData.email || ''}
                  onChange={handleChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-teal-600 focus:border-teal-600 block w-full p-2.5"
                  required
                />
              </div>
              <div>
                <label
                  htmlFor="phoneNumber"
                  className="block mb-2 text-sm font-medium text-gray-900 "
                >
                  Số điện thoại
                </label>
                <input
                  type="number"
                  name="phoneNumber"
                  id="phoneNumber"
                  placeholder="nhập số điện thoại"
                  value={formData.phoneNumber || ''}
                  onChange={handleChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-teal-600 focus:border-teal-600 block w-full p-2.5"
                  required
                />
              </div>
              <button
                onClick={handleSave}
                className="w-full text-white bg-teal-600 hover:bg-teal-700 focus:ring-4 focus:outline-none focus:ring-teal-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
              >
                Đăng ký
              </button>
              <p className="text-sm font-light text-gray-500 ">
                Đã có tài khoản?{" "}
                <a
                  href="/dang-nhap"
                  className="font-medium text-teal-600 hover:underline "
                >
                  Đăng nhập
                </a>
              </p>
            </form>
          </div>
        </div>
      </div>
    </section>
  );
}
