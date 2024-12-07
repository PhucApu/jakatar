import { useState } from 'react';
import { register } from '../../api/services/user/customerService';
import { useNavigate } from 'react-router-dom';
import { Alert } from 'flowbite-react';

interface SignUpForm {
  username: string;
  fullName: string;
  email: string;
  pass: string;
  phoneNumber: string;
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
    <section className='bg-gray-50'>
      <div className='flex flex-col items-center justify-center px-6 py-8 mx-auto'>
        <div className='w-full bg-white rounded-lg shadow md:mt-0 sm:max-w-md xl:p-0'>
          <div className='p-6 space-y-4 md:space-y-6 sm:p-8'>
            <h1 className='text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl'>
              Đăng ký
            </h1>
            <form className='space-y-4 md:space-y-6' onSubmit={handleSubmit}>
              {error && <Alert color='failure'>{error}</Alert>}
              <div>
                <label
                  htmlFor='fullName'
                  className='block mb-2 text-sm font-medium text-gray-900 dark:text-white'
                >
                  Họ và tên
                </label>
                <input
                  type='text'
                  name='fullName'
                  id='fullName'
                  className='bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-teal-600 focus:border-teal-600 block w-full p-2.5'
                  placeholder='Nhập họ và tên'
                  value={signUpForm.fullName}
                  onChange={handleInputChange}
                  required
                />
              </div>
              <div>
                <label
                  htmlFor='phoneNumber'
                  className='block mb-2 text-sm font-medium text-gray-900 dark:text-white'
                >
                  Số điện thoại
                </label>
                <input
                  type='tel'
                  name='phoneNumber'
                  id='phoneNumber'
                  className='bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-teal-600 focus:border-teal-600 block w-full p-2.5'
                  placeholder='Nhập SĐT'
                  value={signUpForm.phoneNumber}
                  onChange={handleInputChange}
                  required
                />
              </div>
              <div>
                <label
                  htmlFor='username'
                  className='block mb-2 text-sm font-medium text-gray-900 dark:text-white'
                >
                  Tên tài khoản
                </label>
                <input
                  type='text'
                  name='username'
                  id='username'
                  className='bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-teal-600 focus:border-teal-600 block w-full p-2.5'
                  placeholder='Nhập tên tài khoản'
                  value={signUpForm.username}
                  onChange={handleInputChange}
                  minLength={6}
                  required
                />
              </div>
              <div>
                <label
                  htmlFor='email'
                  className='block mb-2 text-sm font-medium text-gray-900 dark:text-white'
                >
                  Email
                </label>
                <input
                  type='email'
                  name='email'
                  id='email'
                  className='bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-teal-600 focus:border-teal-600 block w-full p-2.5'
                  placeholder='Nhập email'
                  value={signUpForm.email}
                  onChange={handleInputChange}
                  required
                />
              </div>
              <div>
                <label htmlFor='pass' className='block mb-2 text-sm font-medium text-gray-900'>
                  Mật khẩu
                </label>
                <input
                  type='password'
                  name='pass'
                  id='pass'
                  placeholder='••••••••'
                  className='bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-teal-600 focus:border-teal-600 block w-full p-2.5'
                  value={signUpForm.pass}
                  onChange={handleInputChange}
                  required
                />
              </div>
              <div>
                <label
                  htmlFor='passwordConfirm'
                  className='block mb-2 text-sm font-medium text-gray-900'
                >
                  Nhập lại mật khẩu
                </label>
                <input
                  type='password'
                  name='passwordConfirm'
                  id='passwordConfirm'
                  placeholder='••••••••'
                  className='bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-teal-600 focus:border-teal-600 block w-full p-2.5'
                  value={confirmPassword}
                  onChange={handleConfirmPasswordChange}
                  required
                />
              </div>
              <button
                type='submit'
                className='w-full text-white bg-teal-600 hover:bg-teal-700 focus:ring-4 focus:outline-none focus:ring-teal-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center'
                disabled={loading}
              >
                Đăng ký
              </button>
              <p className='text-sm font-light text-gray-500'>
                Đã có tài khoản?{' '}
                <a href='/dang-nhap' className='font-medium text-teal-600 hover:underline'>
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
